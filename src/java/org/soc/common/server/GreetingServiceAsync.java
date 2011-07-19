package org.soc.common.server;

import org.soc.common.actions.ValidateResult;
import org.soc.common.actions.lobby.LobbyAction;
import org.soc.common.lobby.LoginResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

/** The async counterpart of <code>GreetingService</code>. */
public interface GreetingServiceAsync
{
    void send(LobbyAction action, AsyncCallback<ValidateResult> callback);

    /** Login and setup a CometSession on the chat server.
     * 
     * @param username
     * @throws ChatException */
    public void login(String username, String password,
                    AsyncCallback<LoginResponse> callback);

    public void loginAnonymous(String username,
                    AsyncCallback<LoginResponse> callback);

    public void register(String username, String password,
                    AsyncCallback<RegisterResult> registration);

    public void isAvailableName(String username,
                    AsyncCallback<Boolean> available);
}
