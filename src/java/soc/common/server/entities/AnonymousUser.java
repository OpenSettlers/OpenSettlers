package soc.common.server.entities;

public class AnonymousUser implements User
{
    private static final long serialVersionUID = 9200844322848177617L;
    private String name;
    private int id;
    private int gamesPlayed;
    private boolean isRegistered;
    private String password;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public User setName(String name)
    {
        this.name = name;
        return this;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public User setId(int id)
    {
        this.id = id;
        return this;
    }

    @Override
    public int getGamesPlayed()
    {
        return 0;
    }

    @Override
    public User setGamesPlayed(int gamesPlayed)
    {
        return this;
    }

    @Override
    public User setRegistered(boolean isRegistered)
    {
        return this;
    }

    @Override
    public boolean isRegistered()
    {
        return false;
    }

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public User setPassword(String password)
    {
        return this;
    }

    @Override
    public boolean isAnonymous()
    {
        return true;
    }

}
