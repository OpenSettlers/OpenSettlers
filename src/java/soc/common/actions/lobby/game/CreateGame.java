package soc.common.actions.lobby.game;

import soc.common.actions.ValidateResult;
import soc.common.actions.Invalid;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;

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
     * soc.common.actions.lobby.AbstractLobbyAction#createServerLobbyAction(
     * soc.common.server.lobbyActions.ServerLobbyActionFactory)
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
     * @see soc.common.actions.lobby.AbstractLobbyAction#isValid(soc.common.lobby.Lobby,
     * soc.common.actions.ValidateResult)
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

        return validated;
    }
}
