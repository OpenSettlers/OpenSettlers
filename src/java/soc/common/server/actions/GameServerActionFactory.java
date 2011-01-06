package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.server.GameServer;

public interface GameServerActionFactory
{
    /*
     * Returns associated server action for given GameAction.
     */
    public ServerAction createServerAction(GameAction action,
            GameServer gameServer);
}
