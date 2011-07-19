package org.soc.gwt.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

import org.soc.common.actions.lobby.LobbyAction;
import org.soc.common.server.ServerService;
import org.soc.common.server.entities.Player;
import org.soc.common.server.entities.User;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServerServiceImpl extends RemoteServiceServlet implements
        ServerService
{
    private static final long serialVersionUID = -6382128923291927955L;
    private ConcurrentMap<User, CometSession> users = new ConcurrentHashMap<User, CometSession>();

    @Override
    public User login(String nickName, String password)
    {
        // Get or create the HTTP session for the browser
        HttpSession httpSession = getThreadLocalRequest().getSession();
        // Get or create the Comet session for the browser
        CometSession cometSession = CometServlet.getCometSession(httpSession);

        User user = new Player();
        user.setName(nickName);
        user.setPassword(password);

        // Remember the user name for the
        httpSession.setAttribute("playername", user.getName());

        // setup the mapping of user names to CometSessions
        if (users.putIfAbsent(user, cometSession) != null)
        {
            // some one else has already logged in with this user name
            httpSession.invalidate();
        }

        return user;
    }

    @Override
    public void logout(User user)
    {
        // check if there is a HTTP session setup.
        HttpSession httpSession = getThreadLocalRequest().getSession(false);
        if (httpSession == null)
        {
            // throw new ChatException("User: " + username +
            // " is not logged in: no http session");
        }

        // check if there is a Comet session setup. In a larger application the
        // HTTP session may have been
        // setup via other means.
        CometSession cometSession = CometServlet.getCometSession(httpSession,
                false);
        if (cometSession == null)
        {
            // throw new ChatException("User: " + username +
            // " is not logged in: no comet session");
        }

        // check the user name parameter matches the HTTP sessions user name
        if (!user.getName().equals(httpSession.getAttribute("playername")))
        {
            // throw new ChatException("User: " + username +
            // " is not logged in on this session");
        }

        // remove the mapping of user name to CometSession
        users.remove(user, cometSession);
        httpSession.invalidate();
    }

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
        String username = (String) httpSession.getAttribute("username");
        if (username == null)
        {
            // throw new
            // ChatException("not logged in: no http session username");
        }

        for (Map.Entry<User, CometSession> entry : users.entrySet())
        {
            entry.getValue().enqueue(action);
        }
    }

}
