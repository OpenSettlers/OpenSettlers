package org.soc.common.actions.lobby.game;

import org.soc.common.actions.Invalid;
import org.soc.common.actions.ValidateResult;
import org.soc.common.actions.lobby.AbstractLobbyAction;
import org.soc.common.actions.lobby.LobbyAction;
import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.Lobby;

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
     * @see org.soc.common.actions.lobby.AbstractLobbyAction#isValid(org.soc.common.lobby.Lobby,
     * org.soc.common.actions.ValidateResult)
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