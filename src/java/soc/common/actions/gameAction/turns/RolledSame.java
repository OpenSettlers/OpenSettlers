package soc.common.actions.gameAction.turns;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;

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
        return gamePhase instanceof DetermineFirstPlayerGamePhase;
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
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory()
                .createRolledSameBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createRolledSameBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory().createRolledSameBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createRolledSameBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
