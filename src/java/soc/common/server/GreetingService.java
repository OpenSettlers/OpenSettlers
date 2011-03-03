package soc.common.server;

import soc.common.actions.lobby.LobbyAction;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("chat")
public interface GreetingService extends RemoteService
{
    void send(LobbyAction action);

    public LoginResponse login(String username);
}
