package soc.gwt.server;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;
import soc.common.actions.Invalid;
import soc.common.actions.Valid;
import soc.common.actions.ValidateResult;
import soc.common.actions.lobby.LobbyAction;
import soc.common.actions.lobby.LobbyChat;
import soc.common.actions.lobby.UserJoined;
import soc.common.core.Core;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;
import soc.common.lobby.LobbyImpl;
import soc.common.lobby.LoginResponse;
import soc.common.server.GreetingService;
import soc.common.server.LobbyServer;
import soc.common.server.LoginResponseImpl;
import soc.common.server.RegisterResult;
import soc.common.server.entities.AnonymousUser;
import soc.common.server.entities.Player;
import soc.common.server.entities.RegisterResultImpl;
import soc.common.server.entities.User;
import soc.common.server.entities.UserProvider;
import soc.common.server.lobbyActions.DefaultServerLobbyActionFactory;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;
import soc.gwt.server.database.OdbUserProvider;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/** The server side implementation of the RPC service. */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
                GreetingService, LobbyServer
{
    private ConcurrentMap<User, CometSession> users = new ConcurrentHashMap<User, CometSession>();
    private ConcurrentMap<User, GameInfo> games = new ConcurrentHashMap<User, GameInfo>();
    private ObjectContainer database;
    private ServerLobbyActionFactory factory = new DefaultServerLobbyActionFactory(
                    this);
    private Lobby lobby = new LobbyImpl();
    private Random random = new Random();
    private static final transient Valid valid = new Valid();
    private UserProvider userProvider;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException
    {
        // Init database
        database = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                        "database.db");

        // Create db4o user provider
        userProvider = new OdbUserProvider(database);
    }

    /** @see net.zschech.gwt.chat.client.ChatService#send(java.lang.String) */
    @Override
    public ValidateResult send(LobbyAction action)
    {
        // check if there is a HTTP session setup.
        HttpSession httpSession = getThreadLocalRequest().getSession(false);
        if (httpSession == null)
            return new Invalid("not logged in: no http session");

        // Grab user
        User user = getUserById(action.getUserId());

        // Set the User reference on the action instance
        if (user != null)
            action.setUser(user);

        // get the user name for the HTTP session.
        User userFromSession = (User) httpSession.getAttribute("user");
        if (userFromSession == null)
            return new Invalid("not logged in: no http session username");

        // Check if the user doesn't tamper with the data
        if (user.getId() != action.getUserId())
            return new Invalid(
                            "UserID differs from userID in session. Something is baaaaad");

        // Only the ID is sent, not the user instance, so set the user on the LobbyAction
        action.setUser(user);

        performLobbyAction(action);

        return valid;
    }

    /*
     * Returns a User having given userId. Returns null, if such a user is not logged in.
     */
    private User getUserById(int id)
    {
        for (User user : users.keySet())
            if (user.getId() == id)
                return user;

        return null;
    }

    /*
     * Sends given action to all the users in the lobby
     * 
     * @see soc.common.server.LobbyServer#sendToAll(soc.common.actions.lobby.LobbyAction)
     */
    @Override
    public void sendToAll(LobbyAction action)
    {
        for (Map.Entry<User, CometSession> entry : users.entrySet())
            entry.getValue().enqueue(action);
    }

    /*
     * Sends given action to all users in the lobby, except to given exlcuded user
     * 
     * @see soc.common.server.LobbyServer#sendToAllExcept(soc.common.server.entities.User,
     * soc.common.actions.lobby.LobbyAction)
     */
    @Override
    public void sendToAllExcept(User excludedUser, LobbyAction action)
    {
        for (Map.Entry<User, CometSession> entry : users.entrySet())
            if (!entry.getValue().equals(excludedUser))
                entry.getValue().enqueue(action);
    }

    @Override
    public LoginResponse login(String username, String password)
    {
        // Grab user from database
        User user = userProvider.getUser(username, password);

        if (user == null)
            return new LoginResponseImpl(new Invalid(
                            "Could not find registered user with name \""
                                            + username + "\""));

        // Get or create the HTTP session for the browser
        HttpSession httpSession = getThreadLocalRequest().getSession();

        // Get or create the Comet session for the browser
        CometSession cometSession = CometServlet.getCometSession(httpSession);

        // Remember the user
        httpSession.setAttribute("user", user);

        // setup the mapping of user names to CometSessions
        if (users.putIfAbsent(user, cometSession) != null)
        {
            // some one else has already logged in with this user name
            httpSession.invalidate();
            return new LoginResponseImpl(new Invalid("User: " + username
                            + " already logged in"));
        }

        notifyUserJoined(user);

        return createLoginResponse(user);
    }
    /*
     * Notifies all other players that given player has joined the game
     */
    private void notifyUserJoined(User user)
    {
        // Create a new UserJoined LobbyAction
        UserJoined userJoined = new UserJoined();
        userJoined.setJoinedUser(user);
        userJoined.setUser(user);

        // Send it to everyone but the joined user
        sendToAllExcept(user, userJoined);
    }

    /*
     * Logs given username in as an anonymous user
     * 
     * @see soc.common.server.GreetingService#loginAnonymous(java.lang.String)
     */
    @Override
    public LoginResponse loginAnonymous(String username)
    {
        // Bugger out when the username already exists
        if (userProvider.getUserByName(username) != null)
            return new LoginResponseImpl(new Invalid("Username already exists"));

        User user = new AnonymousUser().setName(username);

        // Get or create the HTTP session for the browser
        HttpSession httpSession = getThreadLocalRequest().getSession();

        // Get or create the Comet session for the browser
        CometSession cometSession = CometServlet.getCometSession(httpSession);

        // Remember the user name for the
        httpSession.setAttribute("user", user);

        // setup the mapping of user names to CometSessions
        if (users.putIfAbsent(user, cometSession) != null)
        {
            // some one else has already logged in with this user name
            httpSession.invalidate();
            return new LoginResponseImpl(new Invalid("User: " + username
                            + " already logged in"));
        }

        notifyUserJoined(user);

        return createLoginResponse(user);
    }

    /*
     * Creates a new LoginResponse for a user logging in
     */
    private synchronized LoginResponse createLoginResponse(User user)
    {
        // Create a new instance
        LoginResponse response = new LoginResponseImpl();

        // Copy list of users to it
        for (Map.Entry<User, CometSession> entry : users.entrySet())
            response.getLoggedInUsers().addUser(entry.getKey());

        // Copy list of games
        for (GameInfo gameInfo : lobby.getGames())
            response.getLobbyGames().add(gameInfo);

        response.setUser(user);

        return response;
    }

    /*
     * Create a server action, perform it and send it to clients
     */
    @Override
    public void performLobbyAction(LobbyAction action)
    {
        // Grab ServerLobbyAction assocaited by the factory
        ServerLobbyAction serverLobbyAction = action
                        .createServerLobbyAction(factory);

        // Actualize lobby by performing the LobbyAction
        serverLobbyAction.perform(lobby);

        // Notify clients
        serverLobbyAction.sendToClients();
    }

    @Override
    public Lobby getLobby()
    {
        return lobby;
    }

    /*
     * Sends given action only to target user
     * 
     * @see soc.common.server.LobbyServer#sendToUser(soc.common.server.entities.User,
     * soc.common.actions.lobby.LobbyAction)
     */
    @Override
    public void sendToUser(User exclusiveUser, LobbyAction action)
    {
        // Grab the session of the given user
        CometSession session = users.get(exclusiveUser);

        // Send the action
        if (session != null)
            session.enqueue(action);
    }

    /*
     * Sends an error message to given user, disguised as a LobbyChat message
     * 
     * @see soc.common.server.LobbyServer#sayError(soc.common.server.entities.User,
     * java.lang.String)
     */
    @Override
    public void sayError(User user, String error)
    {
        // Create a simple LobbyChat instance with the error
        LobbyChat lobbyChat = new LobbyChat();
        lobbyChat.setUser(user);
        lobbyChat.setChatMessage(error);

        // Send it to given user
        sendToUser(user, lobbyChat);
    }

    /*
     * Checks if registration can take place, if so, registers a user
     * 
     * @see soc.common.server.GreetingService#register(java.lang.String, java.lang.String)
     */
    @Override
    public RegisterResult register(String username, String password)
    {
        if (!isAvailableName(username))
            return new RegisterResultImpl(
                            new Invalid("Username ialready taken"));

        ValidateResult result = isValidName(username);
        if (!result.isValid())
            return new RegisterResultImpl(result);

        if (password == null || password.length() == 0 || password == "")
            return new RegisterResultImpl(new Invalid("Password is empty"));

        // Create a new player
        User player = new Player().setName(username).setPassword(password)
                        .setRegistered(true);

        // Store the player
        database.store(player);

        return new RegisterResultImpl(player);
    }

    /*
     * Checks store for existing players with given name, returns true when given name is available
     * 
     * @see soc.common.server.GreetingService#isAvailableName(java.lang.String)
     */
    public boolean isAvailableName(String username)
    {
        // Get a list of players with given name.
        User proto = new Player().setName(username);
        ObjectSet availablePlayers = database.queryByExample(proto);

        // No names equal to given name exist when the list is empty
        return availablePlayers.size() == 0;
    }

    /*
     * Returns invalid when the name is too short, starts with "bot" or contains non alphanumeric
     * characters
     */
    public ValidateResult isValidName(String username)
    {
        if (username.length() < 4)
            return new Invalid("Sorry, nickname must be at least 4 charcters");

        if (username.substring(0, 3).toLowerCase() == "bot")
            return new Invalid("Sorry, nickname cannot start with \"bot\"");

        // Only allow A-Z, a-z, 0-9 for simplicity and security
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]+$");
        if (!pattern.matcher(username).matches())
            return new Invalid(
                            "Sorry, can only use characters [A-Z], [a-z] and [0-9].");

        return Core.valid;
    }
}