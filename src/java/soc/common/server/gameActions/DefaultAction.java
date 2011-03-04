package soc.common.server.gameActions;

import soc.common.actions.gameAction.GameAction;
import soc.common.server.GameServer;

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
     * @see soc.common.server.actions.AbstractServerAction#execute()
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
