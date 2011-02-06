package soc.common.bots;

import soc.common.server.data.User;

public abstract class AbstractBot implements Bot
{
    private static final long serialVersionUID = 4696130339931789360L;
    protected User botUser;

    @Override
    public int getGamesPlayed()
    {
        return botUser.getGamesPlayed();
    }

    @Override
    public int getId()
    {
        return botUser.getId();
    }

    @Override
    public String getName()
    {
        return botUser.getName();
    }

    @Override
    public String getPassword()
    {
        return botUser.getPassword();
    }

    @Override
    public boolean isRegistered()
    {
        return botUser.isRegistered();
    }

    @Override
    public User setGamesPlayed(int gamesPlayed)
    {
        return botUser.setGamesPlayed(gamesPlayed);
    }

    @Override
    public User setId(int id)
    {
        return botUser.setId(id);
    }

    @Override
    public User setName(String name)
    {
        return botUser.setName(name);
    }

    @Override
    public User setPassword(String password)
    {
        return botUser.setPassword(password);
    }

    @Override
    public User setRegistered(boolean isRegistered)
    {
        return botUser.setRegistered(isRegistered);
    }
}
