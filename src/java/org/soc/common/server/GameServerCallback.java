package org.soc.common.server;

import org.soc.common.actions.gameAction.GameAction;

public interface GameServerCallback
{
    public void receive(GameAction gameAction);
}
