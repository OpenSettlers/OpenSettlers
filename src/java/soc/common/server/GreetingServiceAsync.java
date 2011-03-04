package soc.common.server;

import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.LoginResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync
{
    void send(LobbyAction action, AsyncCallback<Void> callback);

    /**
     * Login and setup a CometSession on the chat server.
     * 
     * @param username
     * @throws ChatException
     */
    public void login(String username, AsyncCallback<LoginResponse> callback);
}
