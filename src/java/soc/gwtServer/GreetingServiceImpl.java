package soc.gwtServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;
import soc.common.actions.lobby.LobbyAction;
import soc.common.server.GreetingService;
import soc.common.server.LoginResponse;
import soc.common.server.LoginResponseImpl;
import soc.common.server.entities.Player;
import soc.common.server.entities.User;

import com.db4o.ObjectContainer;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService
{
    ObjectContainer database;
    private static int userid = 0;

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

    private ConcurrentMap<User, CometSession> users = new ConcurrentHashMap<User, CometSession>();

    /**
     * @see net.zschech.gwt.chat.client.ChatService#send(java.lang.String)
     */
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

        // create the chat message
        for (Map.Entry<User, CometSession> entry : users.entrySet())
        {
            entry.getValue().enqueue(action);
        }
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
            response.getLoggedInUsers().add(entry.getKey());

        return response;
    }

}
