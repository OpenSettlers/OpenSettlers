package soc.common.server;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.settings.GameSettings;
import soc.common.server.entities.User;

public class GameInfoImpl implements GameInfo
{
    private int id;
    private String name;
    private User host;
    private List<User> players = new ArrayList<User>();
    private String boardId;
    private GameSettings gameSettings;

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

}
