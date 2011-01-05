package soc.common.server.data;

public class ServerUser implements User
{
    private static final long serialVersionUID = 3977842346753144983L;

    @Override
    public int getGamesPlayed()
    {
        return 0;
    }

    @Override
    public int getId()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "Server";
    }

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public boolean isRegistered()
    {
        return false;
    }

    @Override
    public User setGamesPlayed(int gamesPlayed)
    {
        return null;
    }

    @Override
    public User setId(int id)
    {
        return this;
    }

    @Override
    public User setName(String name)
    {
        return this;
    }

    @Override
    public User setPassword(String password)
    {
        return this;
    }

    @Override
    public User setRegistered(boolean isRegistered)
    {
        return this;
    }

}
