package soc.common.server;

import soc.common.actions.gameAction.GameAction;

public interface GameServerCallback
{
    public void receive(GameAction gameAction);
}
