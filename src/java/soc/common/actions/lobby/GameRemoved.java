package soc.common.actions.lobby;

import soc.common.lobby.Lobby;

public class GameRemoved extends AbstractLobbyAction
{
    private static final long serialVersionUID = 8203243174784950351L;
    private int gameId;

    @Override
    public void perform(Lobby lobby)
    {
        lobby.getGames().remove(lobby.getGames().findById(gameId));
    }

    public GameRemoved setGameId(int gameId)
    {
        this.gameId = gameId;
        return this;
    }

    public int getGameId()
    {
        return gameId;
    }

}
