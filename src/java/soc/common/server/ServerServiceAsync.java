package soc.common.server;

import soc.common.actions.lobby.LobbyAction;
import soc.common.server.entities.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServerServiceAsync
{
    public void login(String nickName, String password,
            AsyncCallback<User> callBack);

    public void logout(User user, AsyncCallback<Void> callBack);

    public void send(LobbyAction action, AsyncCallback<Void> callBack);

}
