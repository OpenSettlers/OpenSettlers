package org.soc.common.lobby.actions;

import static org.soc.common.game.actions.ValidateResult.Invalid.invalid;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;

/** Convenience class for LobbyActions acting upon a GameInfo instance */
public abstract class AbstractGameLobbyAction extends AbstractLobbyAction {
  protected int gameId;

  public int getGameId() {
    return gameId;
  }
  public LobbyAction setGameId(int gameId) {
    this.gameId = gameId;
    return this;
  }
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result) {
    ValidateResult newResult = super.isValid(lobby, result);
    if (!newResult.isValid())
      return newResult;
    GameDto game = lobby.games().byyId(gameId);
    if (game == null)
      return invalid("Game with ID=" + gameId + " does not exist");
    return valid;
  }
}