package soc.common.server.data;

public class BotUser extends AbstractUser implements User
{

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
