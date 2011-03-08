package soc.common.lobby;

import java.io.Serializable;
import java.util.List;

import soc.common.server.entities.User;

/*
 * Lightweight
 */
public interface GameInfo extends Serializable
{
    public int getID();

    public String getName();

    public User getHost();

    public List<User> getPlayers();

    public String getBoardId();

    // public GameSettings getSettings();

    // public GameStatus getGameStatus();

    // public GameInfo setGameStatus(GameStatus newGameStatus);
}
