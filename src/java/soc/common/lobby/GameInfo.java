package soc.common.lobby;

import java.io.Serializable;

import soc.common.game.settings.GameSettings;
import soc.common.game.statuses.GameStatus;
import soc.common.server.entities.User;
import soc.common.server.entities.UserList;

/*
 * Lightweight
 */
public interface GameInfo extends Serializable
{
    public int getID();

    public String getName();

    public User getHost();

    public UserList getUsers();

    public String getBoardId();

    public GameSettings getSettings();

    public GameInfo setSettings(GameSettings settings);

    public GameStatus getGameStatus();

    public GameInfo setGameStatus(GameStatus newGameStatus);

    public LobbyLog getLobbyLog();
}
