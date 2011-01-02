package soc.common.server.data;

public interface AuthenticationProvider
{
    public User authenticate(String name, String password);

}
