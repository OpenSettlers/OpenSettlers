package org.soc.common.actions.lobby.game;

import org.soc.common.actions.ValidateResult;
import org.soc.common.actions.lobby.AbstractLobbyAction;
import org.soc.common.game.statuses.GameStatus;
import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.Lobby;

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

    @Override
    public String getMessage()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void perform(Lobby lobby)
    {
        // Grab associated gameInfo instance
        GameInfo gameInfo = lobby.getGames().findById(gameId);

        // Set new status on it
        if (gameInfo != null)
            gameInfo.setGameStatus(newStatus);
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

        return valid;
    }

}
