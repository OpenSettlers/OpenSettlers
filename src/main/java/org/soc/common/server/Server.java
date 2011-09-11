package org.soc.common.server;

import org.soc.common.lobby.actions.LobbyAction;
import org.soc.common.server.entities.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface Server {
  @RemoteServiceRelativePath("lobby")
  public interface ServerService extends RemoteService {
    /** Login and setup a CometSession on the chat server. */
    public User login(String nickName, String password);
    /** Logout and destroy the CometSession on the chat server. */
    public void logout(User user);
    /** Send a message to all users on the chat server. */
    public void send(LobbyAction action);
  }

  public interface ServerServiceAsync {
    public void login(String nickName, String password,
            AsyncCallback<User> callBack);
    public void logout(User user, AsyncCallback<Void> callBack);
    public void send(LobbyAction action, AsyncCallback<Void> callBack);
  }
}
