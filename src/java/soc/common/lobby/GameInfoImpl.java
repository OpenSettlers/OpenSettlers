package soc.common.lobby;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.settings.GameSettings;
import soc.common.game.statuses.GameStatus;
import soc.common.server.entities.User;

public class GameInfoImpl implements GameInfo
{
    private static final long serialVersionUID = -1030159652269927649L;
    private int id;
    private String name;
    private User host;
    private List<User> players = new ArrayList<User>();
    private String boardId;
    private GameSettings gameSettings;
    private GameStatus gameStatus;

    public GameInfoImpl()
    {
    }

    public GameInfoImpl(String name, User host, String boardId,
            GameSettings gameSettings)
    {
        super();
        this.name = name;
        this.host = host;
        this.boardId = boardId;
        this.gameSettings = gameSettings;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public User getHost()
    {
        return host;
    }

    @Override
    public List<User> getPlayers()
    {
        return players;
    }

    @Override
    public String getBoardId()
    {
        return boardId;
    }

    @Override
    public GameSettings getSettings()
    {
        return gameSettings;
    }

    @Override
    public GameStatus getGameStatus()
    {
        return gameStatus;
    }

    /**
     * @param gameStatus
     *            the gameStatus to set
     */
    public GameInfo setGameStatus(GameStatus gameStatus)
    {
        this.gameStatus = gameStatus;
        return this;
    }
}
