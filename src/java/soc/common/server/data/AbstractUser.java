package soc.common.server.data;

public abstract class AbstractUser implements User
{
    private static final long serialVersionUID = 2830056305909862121L;
    private String name;
    private int id;
    private int gamesPlayed;
    private boolean isRegistered;

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
    public User setName(String name)
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
    public User setId(int id)
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
    public User setGamesPlayed(int gamesPlayed)
    {
        this.gamesPlayed = gamesPlayed;

        return this;
    }

    public User setRegistered(boolean isRegistered)
    {
        this.isRegistered = isRegistered;

        return this;
    }

    public boolean isRegistered()
    {
        return isRegistered;
    }
}
