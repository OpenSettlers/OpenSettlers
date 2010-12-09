package soc.common.server;

public class UserCredentials
{
    private String name;
    private String password;
    
    public UserCredentials(String name, String password)
    {
        this.name = name;
        this.password = password;
    }
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }
}
