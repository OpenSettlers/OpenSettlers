package org.soc.common.lobby.actions;

import org.soc.common.game.GameSettings;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;

/** Notify all active members of the lobby a game has changed it's settings */
public class GameSettingsChanged extends AbstractGameLobbyAction {
  private GameSettings gameSettings;

  public GameSettings getGameSettings() {
    return gameSettings;
  }
  public GameSettingsChanged setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
    return this;
  }
  /** Searches for game with corresponding gameId and changes it's settings */
  // TODO: implement
  @Override public void perform(Lobby lobby) {
    GameDto game = lobby.games().byyId(gameId);
    if (game != null) {}
    //game.setSettings(gameSettings);
    else
      // huh
      throw new RuntimeException("Huh");
  }
}