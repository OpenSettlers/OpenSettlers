package org.soc.common.lobby.actions;

import static org.soc.common.game.actions.ValidateResult.Invalid.invalid;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;

/** A player removed a game he created */
public class RemoveGame extends AbstractGameLobbyAction {
  /** Searches GameInfo instance with given gameId, then removes it from the lobby */
  @Override public void perform(Lobby lobby) {
    GameDto gameToRemove = lobby.games().byyId(gameId);
    //    lobby.games().remove(gameToRemove);
  }
  public RemoveGame setGameId(int gameId) {
    this.gameId = gameId;
    return this;
  }
  public int getGameId() {
    return gameId;
  }
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result) {
    ValidateResult newResult = super.isValid(lobby, result);
    if (!newResult.isValid())
      return newResult;
    GameDto game = lobby.games().byId(gameId);
    if (game == null)
      return invalid("Game with ID=" + gameId + " does not exist");
    if (game.getUsers().contains(user) && game.getUsers().size() > 1)
      return invalid("There are still players in this game");
    return valid;
  }
}
