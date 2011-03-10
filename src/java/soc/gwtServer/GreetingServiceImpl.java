package soc.gwtServer;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;
import soc.common.actions.lobby.LobbyAction;
import soc.common.actions.lobby.LobbyChat;
import soc.common.actions.lobby.UserJoined;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;
import soc.common.lobby.LobbyImpl;
import soc.common.lobby.LoginResponse;
import soc.common.server.GreetingService;
import soc.common.server.JoinResult;
import soc.common.server.LobbyServer;
import soc.common.server.LoginResponseImpl;
import soc.common.server.RegisterResult;
import soc.common.server.UserCredentials;
import soc.common.server.entities.Player;
import soc.common.server.entities.RegisterResultImpl;
import soc.common.server.entities.User;
import soc.common.server.lobbyActions.DefaultServerLobbyActionFactory;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;

import com.db4o.ObjectContainer;
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

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException
    {
        // database =
    }

    /** @see net.zschech.gwt.chat.client.ChatService#send(java.lang.String) */
    @Override
    public void send(LobbyAction action)
    {
        // check if there is a HTTP session setup.
        HttpSession httpSession = getThreadLocalRequest().getSession(false);
        if (httpSession == null)
        {
            // throw new ChatException("not logged in: no http session");
        }

        User user = getUserById(action.getUserId());
        if (user != null)
            action.setUser(user);

        // get the user name for the HTTP session.
        User userFromSession = (User) httpSession.getAttribute("user");
        if (userFromSession == null)
        {
            // throw new
            // ChatException("not logged in: no http session username");
        }

        // Check if the user doesn't tamper with the data
        if (user.getId() != action.getUserId())
        {
            // Throw some exception
        }

        // Only the ID is sent, not the user instance, so set the user on the LobbyAction
        action.setUser(user);

        performLobbyAction(action);
    }
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
        User user = getUser(username, password);

        // Get or create the HTTP session for the browser
        HttpSession httpSession = getThreadLocalRequest().getSession();
        // Get or create the Comet session for the browser
        CometSession cometSession = CometServlet.getCometSession(httpSession);
        // Remember the user name for the
        // httpSession.setAttribute("username", username);

        // setup the mapping of user names to CometSessions
        if (users.putIfAbsent(user, cometSession) != null)
        {
            // some one else has already logged in with this user name
            httpSession.invalidate();
            // throw new ChatException("User: " + username +
            // " already logged in");
        }

        // Notify others a new player joined
        UserJoined userJoined = new UserJoined();
        userJoined.setJoinedUser(user);
        userJoined.setUser(user);
        sendToAllExcept(user, userJoined);

        return createLoginResponse(user);
    }

    private User getUser(String username, String password)
    {
        return new Player().setName(username).setId(random.nextInt(100));
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

    @Override
    public JoinResult join(UserCredentials credentials)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void leave()
    {
        // TODO Auto-generated method stub

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
        return null;
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
        LobbyChat lobbyChat = new LobbyChat();
        lobbyChat.setUser(user);
        lobbyChat.setChatMessage(error);
        sendToUser(user, lobbyChat);
    }

    @Override
    public RegisterResult register(String username, String password)
    {
        RegisterResult result = new RegisterResultImpl(
                        new Player().setName(username));
        return result;
    }

    @Override
    public boolean isAvailableName(String username)
    {
        if (random.nextInt(100) > 20)
            return false;
        else
            return true;
    }

}
