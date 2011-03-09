package soc.common.actions.lobby.game;

import soc.common.actions.ValidateResult;
import soc.common.actions.Invalid;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;

/*
 * A player removed a game he created
 */
public class RemoveGame extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = 8203243174784950351L;

    /*
     * Searches GameInfo instance with given gameId, then removes it from the lobby
     * 
     * @see soc.common.actions.lobby.LobbyAction#perform(soc.common.lobby.Lobby)
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
     * @see soc.common.actions.lobby.AbstractLobbyAction#isValid(soc.common.lobby.Lobby,
     * soc.common.actions.ValidateResult)
     */
    @Override
    public ValidateResult isValid(Lobby lobby, ValidateResult result)
    {
        ValidateResult newResult = super.isValid(lobby, result);

        if (!newResult.isValid())
            return newResult;

        GameInfo game = lobby.getGames().getById(gameId);

        if (game == null)
            return new Invalid("Game with ID=" + gameId
                            + " does not exist");

        if (game.getPlayers().contains(user) && game.getPlayers().size() > 1)
            return new Invalid(
                            "There are still players in this game");

        return validated;
    }

}
