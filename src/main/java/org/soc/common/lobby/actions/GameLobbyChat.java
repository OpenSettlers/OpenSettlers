package org.soc.common.lobby.actions;

import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;

/** Chat on the game room while in LobbyGamePhase */
public class GameLobbyChat extends AbstractGameLobbyAction {
  private String chatMessage = "";

  @Override public void perform(Lobby lobby) {
    GameDto game = lobby.games().byyId(gameId);
    if (game != null)
      game.getLobbyLog().addAction(this);
  }
  public GameLobbyChat setChatMessage(String chatMessage) {
    this.chatMessage = chatMessage;
    return this;
  }
  public String getChatMessage() {
    return chatMessage;
  }
}
