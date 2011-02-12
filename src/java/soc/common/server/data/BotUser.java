package soc.common.server.data;

public class BotUser extends AbstractUser implements User
{
    private static final long serialVersionUID = -3989085089979256450L;

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public User setPassword(String password)
    {
        return null;
    }

}
