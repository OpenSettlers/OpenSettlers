package org.soc.common.server;

import org.soc.common.lobby.Lobby;
import org.soc.common.lobby.actions.LobbyAction;
import org.soc.common.server.entities.User;

public interface LobbyServer extends Server
{
  public void performLobbyAction(LobbyAction lobbyAction);
  public abstract void sendToAllExcept(User excludedUser, LobbyAction action);
  public abstract void sendToAll(LobbyAction action);
  public void sendToUser(User exclusiveUser, LobbyAction action);
  public void sayError(User user, String error);
  public Lobby getLobby();

  public interface LobbyServerCallback
  {
    public void receive(LobbyAction action);
  }
}
