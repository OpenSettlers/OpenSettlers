package soc.common.server;

public interface IServer
{
    public JoinResult join(UserCredentials credentials);
    public void leave();
}
