package soc.common.server;

import soc.common.actions.gameAction.GameAction;

public interface IGameServerCallback
{
    public void receive(GameAction gameAction);
}
