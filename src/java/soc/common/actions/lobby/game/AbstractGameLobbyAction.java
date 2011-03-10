package soc.common.actions.lobby.game;

import soc.common.actions.ValidateResult;
import soc.common.actions.Invalid;
import soc.common.actions.lobby.AbstractLobbyAction;
import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;

/*
 * Convenience class for LobbyActions acting upon a GameInfo instance
 */
public abstract class AbstractGameLobbyAction extends AbstractLobbyAction
{
    private static final long serialVersionUID = -5096430364989033889L;
    protected int gameId;

    /** @return the gameId */
    public int getGameId()
    {
        return gameId;
    }

    /** @param gameId the gameId to set */
    public LobbyAction setGameId(int gameId)
    {
        this.gameId = gameId;
        return this;
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

        GameInfo game = lobby.getGames().findById(gameId);
        if (game == null)
            return new Invalid("Game with ID=" + gameId
                            + " does not exist");

        return valid;
    }
}