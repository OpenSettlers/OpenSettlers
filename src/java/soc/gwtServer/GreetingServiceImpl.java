package soc.gwtServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;
import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.Lobby;
import soc.common.lobby.LoginResponse;
import soc.common.server.GreetingService;
import soc.common.server.JoinResult;
import soc.common.server.LobbyServer;
import soc.common.server.LoginResponseImpl;
import soc.common.server.UserCredentials;
import soc.common.server.entities.Player;
import soc.common.server.entities.User;
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
    private ObjectContainer database;
    private static int userid = 0;
    private ServerLobbyActionFactory factory;
    private Lobby lobby;

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

        // get the user name for the HTTP session.
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
        {
            // throw new
            // ChatException("not logged in: no http session username");
        }

        executeLobbyAction(action);
    }

    @Override
    public void sendToAll(LobbyAction action)
    {
        for (Map.Entry<User, CometSession> entry : users.entrySet())
            entry.getValue().enqueue(action);
    }

    @Override
    public void sendToAllExcept(User excludedUser, LobbyAction action)
    {
        for (Map.Entry<User, CometSession> entry : users.entrySet())
            if (!entry.getValue().equals(excludedUser))
                entry.getValue().enqueue(action);
    }

    @Override
    public LoginResponse login(String username)
    {
        userid++;
        User user = new Player().setName(username).setId(userid);

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

        return createLoginResponse();
    }

    private synchronized LoginResponse createLoginResponse()
    {
        LoginResponse response = new LoginResponseImpl();
        for (Map.Entry<User, CometSession> entry : users.entrySet())
            response.getLoggedInUsers().addUser(entry.getKey());

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
    public void executeLobbyAction(LobbyAction action)
    {
        // Grab ServerLobbyAction assocaited by the factory
        ServerLobbyAction serverLobbyAction = action
                        .createServerLobbyAction(factory);

        // Actualize lobby by performing the LobbyAction
        serverLobbyAction.perform(lobby);

        // Notify clients
        serverLobbyAction.sendToClients();
    }

}
