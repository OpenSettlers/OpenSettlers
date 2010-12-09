package soc.common.server;

import soc.common.actions.gameAction.GameAction;

public interface IGameServer extends IServer
{
    public JoinResult join(UserCredentials credentials);
    public void sendAction(GameAction action);
    public void leave();
}
