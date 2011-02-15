package soc.common.server.entities;

public class UnregisteredUser extends AbstractUser
{
    private static final long serialVersionUID = 2369403106141512435L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.server.data.AbstractUser#isRegistered()
     */
    @Override
    public boolean isRegistered()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.server.data.AbstractUser#setRegistered(boolean)
     */
    @Override
    public User setRegistered(boolean isRegistered)
    {
        return this;
    }

    @Override
    public String getPassword()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User setPassword(String password)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
