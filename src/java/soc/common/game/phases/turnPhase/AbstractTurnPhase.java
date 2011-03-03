package soc.common.game.phases.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.utils.ClassUtils;

public abstract class AbstractTurnPhase implements TurnPhase
{
    private static final long serialVersionUID = 6519636332497912105L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.turnPhase.TurnPhase#isAllowed(soc.common.actions
     * .gameAction.GameAction)
     */
    public boolean isAllowed(GameAction action)
    {
        return action.isAllowed(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#getName()
     */
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#isBeforeDiceRoll()
     */
    public boolean isBeforeDiceRoll()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#isDiceRoll()
     */
    public boolean isDiceRoll()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#isTrading()
     */
    public boolean isTrading()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#isBuilding()
     */
    public boolean isBuilding()
    {
        return false;
    }
}
