package soc.common.server.data;

import java.io.Serializable;

public interface User extends Serializable
{
    public String getName();

    public User setName(String name);

    public int getId();

    public User setId(int id);

    public int getGamesPlayed();

    public User setGamesPlayed(int gamesPlayed);

    public User setRegistered(boolean isRegistered);

    public boolean isRegistered();

    public String getPassword();

    public User setPassword(String password);
}
