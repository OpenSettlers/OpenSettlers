package soc.common.server.actions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;

public class DefaultAction extends AbstractServerAction
{
    protected GameAction action;
    public DefaultAction(GameAction action)
    {
        this.action=action;
    }

    @Override
    public AbstractGameAction getAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
