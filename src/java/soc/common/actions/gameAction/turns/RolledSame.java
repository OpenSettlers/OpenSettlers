package soc.common.actions.gameAction.turns;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.generic.ToolTip;

public class RolledSame extends AbstractGameAction
{
    private static final long serialVersionUID = 1784758212459861730L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
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

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    private int highRoll;
    private List<Integer> sameRollPlayers = new ArrayList<Integer>();

    /**
     * @return the highRoll
     */
    public int getHighRoll()
    {
        return highRoll;
    }

    /**
     * @param highRoll
     *            the highRoll to set
     */
    public RolledSame setHighRoll(int highRoll)
    {
        this.highRoll = highRoll;
        return this;
    }

    public RolledSame setSameRollingPlayers(List<Integer> playerIDs)
    {
        this.sameRollPlayers = playerIDs;
        return this;
    }

    public List<Integer> getSameRolledPlayers()
    {
        return sameRollPlayers;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isDetermineFirstPlayer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        game.advanceTurn();

        // TODO: fix message
        message = "Rolled same: " + highRoll;

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        // TODO: fix message
        return "Highrollers should roll again, highroll:" + highRoll;
    }

    @Override
    public ActionWidget createActionWidget(
            ActionWidgetFactory actionWidgetFactory)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createRolledSameBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createRolledSameBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createRolledSameBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createRolledSameBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
