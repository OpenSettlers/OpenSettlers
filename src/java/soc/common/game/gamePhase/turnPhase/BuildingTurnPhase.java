package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.game.Game;

public class BuildingTurnPhase extends TurnPhase
{
    private TradingTurnPhase tradingTurnPhase;

    public BuildingTurnPhase(TradingTurnPhase tradingTurnPhase)
    {
        this.tradingTurnPhase = tradingTurnPhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        return super.next();
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
        if (action.isAllowed(this))
        {
            action.perform(game);

            // EndTurn endTurn = action as EndTurnAction;
            if (action instanceof EndTurn)
            {
                return new BeforeDiceRollTurnPhase();
            }

            // Players may build anything they can pay for in a turnphase
            return this;
        }
        else
        {
            // Look if the action is allowed in the tradingPhase, and if we may
            // go back
            // to that phase, perform the action and return the phase
            if (action.isAllowed(tradingTurnPhase))
            {
                tradingTurnPhase.processAction(action, game);
                return tradingTurnPhase;
            }
            else
            {
                throw new RuntimeException("We should not reach this code");
            }
        }
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
            // TODO: add chck for game setting
            return action.isAllowed(tradingTurnPhase);
        }
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Build towns, cities and roads";
    }
}
