package org.soc.common.actions.gameAction.trading;

import org.soc.common.actions.gameAction.AbstractGameAction;
import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.Game;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.game.trading.TradeResponse;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import org.soc.gwt.client.images.Resources;

public class RejectTradeOffer extends AbstractGameAction implements
                TradeResponse
{
    private static final long serialVersionUID = 3831905224550592802L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().tradeRejected16(),
                        Resources.icons().tradeRejected32(), Resources.icons()
                                        .tradeRejected48());

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
    private TradeOffer originatingOffer;

    @Override
    public TradeOffer getOriginatingOffer()
    {
        return originatingOffer;
    }

    public TradeResponse setOriginatingOffer(TradeOffer originatingOffer)
    {
        this.originatingOffer = originatingOffer;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#perform(org.soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        game.addTradeResponse(this);

        // TODO: fix message

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        // TODO Auto-generated method stub
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#isExpectedQueueType(
     * org.soc.common.actions.gameAction.GameAction)
     */
    @Override
    public boolean isExpectedQueueType(GameAction actualAction)
    {
        return actualAction instanceof TradeResponse;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isTrading();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }

    @Override
    public void setTradeResources(TradePlayer tradePlayer)
    {
        throw new AssertionError("Can't trade with a rejected offer");
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
        return gameBehaviourFactory.createRejectOfferBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createRejectOfferBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createRejectOfferBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createRejectOfferBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public boolean isAccepted()
    {
        return false;
    }

    @Override
    public boolean isCounterOffer()
    {
        return false;
    }

    @Override
    public boolean isRejection()
    {
        return true;
    }
}
