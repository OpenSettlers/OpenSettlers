package org.soc.common.lobby.actions;

import static org.soc.common.game.actions.ValidateResult.Invalid.invalid;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.GameDto;
import org.soc.common.server.lobbyActions.ServerLobbyAction;
import org.soc.common.server.lobbyActions.ServerLobbyActionFactory;

public class CreateGame extends AbstractGameLobbyAction
{
  private static final long serialVersionUID = 6745433721783796193L;
  private GameDto gameInfo;

  public GameDto getGameInfo()
  {
    return gameInfo;
  }
  public CreateGame setGameInfo(GameDto gameInfo)
  {
    this.gameInfo = gameInfo;
    return this;
  }
  @Override public void perform(Lobby lobby)
  {
    lobby.games().addGame(gameInfo);
  }
  @Override public ServerLobbyAction createServerLobbyAction(
          ServerLobbyActionFactory factory)
  {
    return factory.createNewGameAction(this);
  }
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result) {
    ValidateResult newResult = super.isValid(lobby, result);
    if (!newResult.isValid())
      return newResult;
    if (lobby.games().getByHost(user) != null)
      return invalid("Host already has a game hosted");
    if (lobby.games().getFromGame(user) != null)
      return invalid("User is already in a game");
    return valid;
  }
}
