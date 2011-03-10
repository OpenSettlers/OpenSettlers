package soc.common.actions.lobby.game;

import soc.common.actions.Invalid;
import soc.common.actions.ValidateResult;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;

/*
 * A player leaves a gameroom
 */
public class LeaveGame extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = -734646394338711620L;

    /** @return the gameId */
    public int getGameId()
    {
        return gameId;
    }

    /** @param gameId the gameId to set */
    public LeaveGame setGameId(int gameId)
    {
        this.gameId = gameId;
        return this;
    }

    @Override
    public void perform(Lobby lobby)
    {
        GameInfo game = lobby.getGames().getById(gameId);
        if (game != null)
            game.getUsers().removeUser(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.AbstractLobbyAction#isValid(soc.common.lobby.Lobby,
     * soc.common.actions.ValidateResult)
     */
    @Override
    public ValidateResult isValid(Lobby lobby, ValidateResult result)
    {
        ValidateResult newResult = super.isValid(lobby, result);

        if (!newResult.isValid())
            return newResult;

        if (lobby.getGames().getById(gameId) == null)
            return new Invalid("Game with ID=" + gameId + " does not exist");

        return valid;
    }

}
