package soc.common.server;

import soc.common.actions.gameAction.AbstractGameAction;

public interface IGameServer extends IServer
{
    public JoinResult join(UserCredentials credentials);
    public void sendAction(AbstractGameAction action);
    public void leave();
}
