package soc.common.actions.lobby;

import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;

public class CreateGame extends AbstractLobbyAction
{
    private static final long serialVersionUID = 6745433721783796193L;
    private GameInfo gameInfo;

    /**
     * @return the gameInfo
     */
    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    /**
     * @param gameInfo
     *            the gameInfo to set
     */
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
        return factory.createNewGameAction();
    }
}
