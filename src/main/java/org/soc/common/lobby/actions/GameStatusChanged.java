package org.soc.common.lobby.actions;

import org.soc.common.game.GameStatus;
import org.soc.common.game.actions.ValidateResult;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;

/*
 * Represents a status change in a game
 */
public class GameStatusChanged extends AbstractLobbyAction
{
  private static final long serialVersionUID = -1626089108739910240L;
  private GameStatus newStatus;
  private int gameId;

  /** @return the newStatus */
  public GameStatus getNewStatus()
  {
    return newStatus;
  }
  @Override public String getMessage()
  {
    // TODO fix message
    return null;
  }
  @Override public void perform(Lobby lobby)
  {
    // Grab associated gameInfo instance
    GameDto gameInfo = lobby.games().byyId(gameId);
    // Set new status on it
    //    if (gameInfo != null)
    //      gameInfo.setGameStatus(newStatus);
  }
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result)
  {
    ValidateResult newResult = super.isValid(lobby, result);
    if (!newResult.isValid())
      return newResult;
    return valid;
  }
}
