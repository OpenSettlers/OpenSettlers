package org.soc.common.actions.lobby.game;

import org.soc.common.actions.Invalid;
import org.soc.common.actions.ValidateResult;
import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.lobbyActions.ServerLobbyAction;
import org.soc.common.server.lobbyActions.ServerLobbyActionFactory;

public class CreateGame extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = 6745433721783796193L;
    private GameInfo gameInfo;

    /** @return the gameInfo */
    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    /** @param gameInfo
     *            the gameInfo to set */
    public CreateGame setGameInfo(GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
        return this;
    }

    @Override
    public void perform(Lobby lobby)
    {
        lobby.getGames().addGame(gameInfo);
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.lobby.AbstractLobbyAction#createServerLobbyAction(
     * org.soc.common.server.lobbyActions.ServerLobbyActionFactory)
     */
    @Override
    public ServerLobbyAction createServerLobbyAction(
                    ServerLobbyActionFactory factory)
    {
        return factory.createNewGameAction(this);
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

        if (lobby.getGames().getByHost(user) != null)
            return new Invalid("Host already has a game hosted");

        if (lobby.getGames().getFromGame(user) != null)
            return new Invalid("User is already in a game");

        return valid;
    }
}
