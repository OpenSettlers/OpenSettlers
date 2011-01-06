package soc.common.server;

public interface Server
{
    public JoinResult join(UserCredentials credentials);
    public void leave();
}
