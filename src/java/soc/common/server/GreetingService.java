package soc.common.server;

import soc.common.actions.ValidateResult;
import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.LoginResponse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/** The client side stub for the RPC service. */
@RemoteServiceRelativePath("chat")
public interface GreetingService extends RemoteService
{
    public ValidateResult send(LobbyAction action);

    public LoginResponse login(String username, String password);

    public LoginResponse loginAnonymous(String username);

    public RegisterResult register(String username, String password);

    public boolean isAvailableName(String username);
}
