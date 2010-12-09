package soc.common.server;

import soc.common.actions.gameAction.GameAction;

public interface IServer
{
    public JoinResult join(UserCredentials credentials);
    public void leave();
}
