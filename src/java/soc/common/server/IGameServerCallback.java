package soc.common.server;

import soc.common.actions.gameAction.GameAction;
import soc.common.server.actions.ServerAction;

public interface IGameServerCallback
{
    public void receive(ServerAction serverAction);

    public void receive(GameAction gameAction);
}
