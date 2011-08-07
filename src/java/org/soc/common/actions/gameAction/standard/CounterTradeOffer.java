package org.soc.common.actions.gameAction.standard;

import org.soc.common.actions.gameAction.AbstractGameAction;
import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.trading.TradeOffer;
import org.soc.common.actions.gameAction.trading.TradePlayer;
import org.soc.common.board.resources.ResourceList;
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

public class CounterTradeOffer extends AbstractGameAction implements
                TradeResponse
{
    private static final long serialVersionUID = 7506550189521999145L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().tradeCountered16(),
                        Resources.icons().tradeCountered32(), Resources.icons()
                                        .tradeCountered48());

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
    private ResourceList offeredResources = new ResourceList();
    private ResourceList requestedResources = new ResourceList();
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
     * Offered resources as seen from the offering players'perspective
     */
    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    /*
     * Requested resources as seen from the offering players' perspective
     */
    public ResourceList getRequestedResources()
    {
        return requestedResources;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#isValid(org.soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
        {
            return false;
        }

        if (offeredResources == null || requestedResources == null)
        {
            invalidMessage = "OfferedResources and RequestedResources cannot be null";
            return false;
        }

        if (offeredResources.size() == 0 || requestedResources.size() == 0)
        {
            invalidMessage = "OfferedResources and RequestedResources cannot be empty";
            return false;
        }

        if (!player.getResources().hasAtLeast(requestedResources))
        {
            invalidMessage = "Player does not have offered resources";
            return false;
        }

        if (originatingOffer.getResponses().containsResponse(player))
        {
            invalidMessage = "Player already has responded to the offer";
            return false;
        }

        return true;
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
        return null;
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
        tradePlayer.getRequestedResources().addList(requestedResources);
        tradePlayer.getOfferedResources().addList(offeredResources);
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
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createCounterTradeOfferBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createCounterTradeOfferBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createCounterTradeOfferBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createCounterTradeOfferBehaviour(this);
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
        return true;
    }

    @Override
    public boolean isRejection()
    {
        return false;
    }
}
