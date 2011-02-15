package soc.common.server.entities;

public class Player implements User
{
    private static final long serialVersionUID = 2830056305909862121L;
    private String name;
    private int id;
    private int gamesPlayed;
    private boolean isRegistered;
    private String password;

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public Player setName(String name)
    {
        this.name = name;

        return this;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public Player setId(int id)
    {
        this.id = id;

        return this;
    }

    /**
     * @return the gamesPlayed
     */
    public int getGamesPlayed()
    {
        return gamesPlayed;
    }

    /**
     * @param gamesPlayed
     *            the gamesPlayed to set
     */
    public Player setGamesPlayed(int gamesPlayed)
    {
        this.gamesPlayed = gamesPlayed;

        return this;
    }

    public Player setRegistered(boolean isRegistered)
    {
        this.isRegistered = isRegistered;

        return this;
    }

    public boolean isRegistered()
    {
        return isRegistered;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }
}
