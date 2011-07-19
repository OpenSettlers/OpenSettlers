package org.soc.common.game.phases.turnPhase;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.Game;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class TradingTurnPhase extends AbstractTurnPhase
{
    private static final long serialVersionUID = -6990539284724565304L;
    private BuildingTurnPhase buildPhase;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(
                        Resources.icons().tradingTurnPhase16(), Resources
                                        .icons().tradingTurnPhase32(),
                        Resources.icons().tradingTurnPhase48());

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

    public TradingTurnPhase()
    {
        buildPhase = new BuildingTurnPhase(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.gamePhase.turnPhase.TurnPhase#isAllowed(org.soc.common.actions
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
            if (action.isAllowed(buildPhase))
            {
                return true;
            } else
            {
                return false;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.turnPhase.TurnPhase#next()
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
     * org.soc.common.game.gamePhase.turnPhase.TurnPhase#processAction(org.soc.common
     * .actions.gameAction.GameAction, org.soc.common.game.Game)
     */
    @Override
    public TurnPhase processAction(GameAction action, Game game)
    {
        // If the action is allowed to be executed in this phase, do it
        if (action.isAllowed(this))
        {
            action.perform(game);
            return this;
        } else
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
     * @see org.soc.common.game.gamePhase.turnPhase.AbstractTurnPhase#isTrading()
     */
    @Override
    public boolean isTrading()
    {
        return true;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
