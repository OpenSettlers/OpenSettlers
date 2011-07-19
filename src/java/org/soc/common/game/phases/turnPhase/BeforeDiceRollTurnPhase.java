package org.soc.common.game.phases.turnPhase;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.Game;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class BeforeDiceRollTurnPhase extends AbstractTurnPhase
{
    private static final long serialVersionUID = -2334736656517691453L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                        .beforeDiceRollTurnPhase16(), Resources.icons()
                        .beforeDiceRollTurnPhase32(), Resources.icons()
                        .beforeDiceRollTurnPhase48());

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

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        return new RollDiceTurnPhase();
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
        return this;
        // return super.processAction(action, game);
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Start turn";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.gamePhase.turnPhase.AbstractTurnPhase#isBeforeDiceRoll()
     */
    @Override
    public boolean isBeforeDiceRoll()
    {
        return true;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
