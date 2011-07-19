package org.soc.common.actions.lobby.game;

import org.soc.common.actions.Invalid;
import org.soc.common.actions.ValidateResult;
import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.Lobby;

/*
 * A player removed a game he created
 */
public class RemoveGame extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = 8203243174784950351L;

    /*
     * Searches GameInfo instance with given gameId, then removes it from the lobby
     * 
     * @see org.soc.common.actions.lobby.LobbyAction#perform(org.soc.common.lobby.Lobby)
     */
    @Override
    public void perform(Lobby lobby)
    {
        GameInfo gameToRemove = lobby.getGames().findById(gameId);
        lobby.getGames().remove(gameToRemove);
    }

    public RemoveGame setGameId(int gameId)
    {
        this.gameId = gameId;
        return this;
    }

    public int getGameId()
    {
        return gameId;
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

        GameInfo game = lobby.getGames().getById(gameId);

        if (game == null)
            return new Invalid("Game with ID=" + gameId + " does not exist");

        if (game.getUsers().contains(user) && game.getUsers().size() > 1)
            return new Invalid("There are still players in this game");

        return valid;
    }

}
