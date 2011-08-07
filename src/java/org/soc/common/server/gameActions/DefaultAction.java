package org.soc.common.server.gameActions;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.server.GameServer;

/*
 * Any action not requiring any special server side logic
 */
public class DefaultAction implements ServerAction
{
    protected GameAction action;
    protected GameServer gameServer;

    public DefaultAction(GameServer gameServer, GameAction action)
    {
        this.action = action;
        this.gameServer = gameServer;
    }

    @Override
    public GameAction getAction()
    {
        return action;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.server.actions.AbstractServerAction#execute()
     */
    @Override
    public void execute()
    {
        gameServer.getGame().performAction(action);
    }

    @Override
    public GameAction getOpponentAction()
    {
        return action;
    }
}
