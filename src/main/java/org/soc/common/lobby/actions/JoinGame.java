package org.soc.common.lobby.actions;

import static org.soc.common.game.actions.ValidateResult.Invalid.invalid;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;

public class JoinGame extends AbstractGameLobbyAction {
  @Override public void perform(Lobby lobby) {
    GameDto game = lobby.games().byId(gameId);
    //    if (game != null)
    //      game.getUsers().addUser(user);
  }
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result) {
    ValidateResult newResult = super.isValid(lobby, result);
    if (!newResult.isValid())
      return newResult;
    if (lobby.games().byId(gameId) == null)
      return invalid("Game with ID=" + gameId + " does not exist");
    return valid;
  }
}
