package soc.common.game.phases.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

public class TradingTurnPhase extends AbstractTurnPhase
{
    private static final long serialVersionUID = -6990539284724565304L;
    private BuildingTurnPhase buildPhase;

    public TradingTurnPhase()
    {
        buildPhase = new BuildingTurnPhase(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.turnPhase.TurnPhase#isAllowed(soc.common.actions
     * .gameAction.GameAction)
     */
    @Override
    public boolean isAllowed(GameAction action)
    {
        if (action.isAllowed(this))
        {
            return true;
        }
        else
        {
            if (action.isAllowed(buildPhase))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        return buildPhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.turnPhase.TurnPhase#processAction(soc.common
     * .actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public TurnPhase processAction(GameAction action, Game game)
    {
        // If the action is allowed to be executed in this phase, do it
        if (action.isAllowed(this))
        {
            action.perform(game);
            return this;
        }
        else
        // If action is not allowed to execute, check if buildphase allows it.
        // If so, move to
        // buildphase
        {
            if (buildPhase.isAllowed(action))
            {
                buildPhase.processAction(action, game);
                return buildPhase;
            }
            throw new RuntimeException("Should not reach this code");
        }
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Trade with opponents";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.AbstractTurnPhase#isTrading()
     */
    @Override
    public boolean isTrading()
    {
        return true;
    }
}
