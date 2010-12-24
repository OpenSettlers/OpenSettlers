package soc.common.actions.gameAction.turnActions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;


public class RolledSame extends AbstractGameAction
{
    private static final long serialVersionUID = 1784758212459861730L;
    private int highRoll;

    /**
     * @return the highRoll
     */
    public int getHighRoll()
    {
        return highRoll;
    }

    /**
     * @param highRoll the highRoll to set
     */
    public RolledSame setHighRoll(int highRoll)
    {
        this.highRoll = highRoll;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        // TODO Auto-generated method stub
        return false;
    }
}
