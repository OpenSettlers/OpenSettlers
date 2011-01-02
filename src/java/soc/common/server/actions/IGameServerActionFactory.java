package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.server.GameServer;

public interface IGameServerActionFactory
{
    /*
     * Returns associated server action for given GameAction. Returns null if no
     * assocated serveraction exists.
     */
    public ServerAction createServerAction(GameAction action,
            GameServer gameServer);
}
