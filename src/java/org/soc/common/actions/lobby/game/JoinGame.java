package org.soc.common.actions.lobby.game;

import org.soc.common.actions.Invalid;
import org.soc.common.actions.ValidateResult;
import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.Lobby;

public class JoinGame extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = 1568566725321655808L;

    @Override
    public void perform(Lobby lobby)
    {
        GameInfo game = lobby.getGames().getById(gameId);
        if (game != null)
            game.getUsers().addUser(user);
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

        if (lobby.getGames().getById(gameId) == null)
            return new Invalid("Game with ID=" + gameId + " does not exist");

        return valid;
    }

}
