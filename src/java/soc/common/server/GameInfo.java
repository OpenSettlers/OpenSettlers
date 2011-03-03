package soc.common.server;

import java.util.List;

import soc.common.game.settings.GameSettings;
import soc.common.server.entities.User;

public interface GameInfo
{
    public int getID();

    public String getName();

    public User getHost();

    public List<User> getPlayers();

    public String getBoardId();

    public GameSettings getSettings();
}
