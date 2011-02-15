package soc.common.server.entities;

public interface AuthenticationProvider
{
    public User authenticate(String name, String password);

}
