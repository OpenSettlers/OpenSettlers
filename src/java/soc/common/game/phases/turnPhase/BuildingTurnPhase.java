package soc.common.game.phases.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.game.Game;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class BuildingTurnPhase extends AbstractTurnPhase
{
    private static final long serialVersionUID = 8241030068782844685L;
    private TradingTurnPhase tradingTurnPhase;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                        .buildingTurnPhase16(), Resources.icons()
                        .buildingTurnPhase32(), Resources.icons()
                        .buildingTurnPhase48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

    };

    public BuildingTurnPhase()
    {
    }

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
        return new BeforeDiceRollTurnPhase();
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
        } else
        {
            // Look if the action is allowed in the tradingPhase, and if we may
            // go back
            // to that phase, perform the action and return the phase
            if (action.isAllowed(tradingTurnPhase))
            {
                tradingTurnPhase.processAction(action, game);
                return tradingTurnPhase;
            } else
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
        } else
        {
            // TODO: add chck for game setting
            return action.isAllowed(tradingTurnPhase);
        }
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Build or buy stuff";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.AbstractTurnPhase#isBuilding()
     */
    @Override
    public boolean isBuilding()
    {
        return true;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
