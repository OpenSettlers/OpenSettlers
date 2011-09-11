package org.soc.common.server.lobbyActions;

import org.soc.common.lobby.Lobby;
import org.soc.common.lobby.actions.CreateGame;
import org.soc.common.lobby.actions.LobbyAction;
import org.soc.common.server.GameDto;
import org.soc.common.server.LobbyServer;

public interface ServerLobbyAction {
  public LobbyAction getLobbyAction();
  public void sendToClients();
  public void perform(Lobby lobby);

  public class CreateGameServerAction implements ServerLobbyAction {
    private CreateGame createGame;
    private LobbyServer lobbyServer;

    public CreateGameServerAction(LobbyServer lobbyServer, CreateGame createGame) {
      super();
      this.lobbyServer = lobbyServer;
      this.createGame = createGame;
    }
    @Override public LobbyAction getLobbyAction() {
      return createGame;
    }
    @Override public void sendToClients() {
      lobbyServer.sendToAll(createGame);
    }
    @Override public void perform(Lobby lobby) {
      // Grab current gameroom user is in, when present
      GameDto exisitingGame = lobbyServer.getLobby().games()
              .getByHost(createGame.getUser());
      // Perform the action when valid or bugger out when the user is already in some room
      if (exisitingGame == null)
        createGame.perform(lobby);
      else
        lobbyServer.sayError(createGame.getUser(), "Already in a game room");
    }
  }

  /** Default implementation for a ServerLobbyAction */
  public class DefaultServerLobbyAction implements ServerLobbyAction {
    private LobbyAction lobbyAction;
    private LobbyServer lobbyServer;

    public DefaultServerLobbyAction(LobbyAction lobbyAction, LobbyServer lobbyServer) {
      super();
      this.lobbyAction = lobbyAction;
      this.lobbyServer = lobbyServer;
    }
    @Override public LobbyAction getLobbyAction() {
      return lobbyAction;
    }
    /** by default, it sends encapsulated LobbyAction to each user on the server */
    @Override public void sendToClients() {
      lobbyServer.sendToAll(lobbyAction);
    }
    /** Calls perform on the encapsulated LobbyAction */
    @Override public void perform(Lobby lobby) {
      lobbyAction.perform(lobby);
    }
  }
}
