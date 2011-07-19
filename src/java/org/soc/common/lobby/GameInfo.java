package org.soc.common.lobby;

import java.io.Serializable;

import org.soc.common.game.settings.GameSettings;
import org.soc.common.game.statuses.GameStatus;
import org.soc.common.server.entities.User;
import org.soc.common.server.entities.UserList;


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
