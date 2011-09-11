package org.soc.common.server.lobbyActions;

import org.soc.common.lobby.actions.LobbyAction;
import org.soc.common.server.LobbyServer;
import org.soc.common.server.lobbyActions.ServerLobbyAction.DefaultServerLobbyAction;

public interface ServerLobbyActionFactory
{
  public ServerLobbyAction createJoinedAction(LobbyAction lobbyAction);
  public ServerLobbyAction createGameRemovedAction(LobbyAction lobbyAction);
  public ServerLobbyAction createLobbyChatAction(LobbyAction lobbyAction);
  public ServerLobbyAction createGameStatusChangedAction(LobbyAction lobbyAction);
  public ServerLobbyAction createNewGameAction(LobbyAction lobbyAction);
  public ServerLobbyAction createDefaultAction(LobbyAction lobbyAction);

  public class DefaultServerLobbyActionFactory implements
          ServerLobbyActionFactory
  {
    private LobbyServer lobbyServer;

    public DefaultServerLobbyActionFactory(LobbyServer lobbyServer)
    {
      super();
      this.lobbyServer = lobbyServer;
    }
    @Override public ServerLobbyAction createJoinedAction(LobbyAction lobbyAction)
    {
      return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }
    @Override public ServerLobbyAction createGameRemovedAction(LobbyAction lobbyAction)
    {
      return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }
    @Override public ServerLobbyAction createLobbyChatAction(LobbyAction lobbyAction)
    {
      return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }
    @Override public ServerLobbyAction createGameStatusChangedAction(
            LobbyAction lobbyAction)
    {
      return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }
    @Override public ServerLobbyAction createNewGameAction(LobbyAction lobbyAction)
    {
      return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }
    @Override public ServerLobbyAction createDefaultAction(LobbyAction lobbyAction)
    {
      return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }
  }
}
