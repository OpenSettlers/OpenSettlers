package soc.common.actions.lobby;

import soc.common.server.GameInfo;
import soc.common.server.Lobby;

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
}
