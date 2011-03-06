package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.trading.TradeResponse;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.images.Resources;

public class CounterTradeOffer extends AbstractGameAction implements
                TradeResponse
{
    private static final long serialVersionUID = 7506550189521999145L;
    private static Meta meta = new Meta()
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
     * soc.common.actions.gameAction.AbstractGameAction#isValid(soc.common.game
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
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
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
     * soc.common.actions.gameAction.AbstractGameAction#isExpectedQueueType(
     * soc.common.actions.gameAction.GameAction)
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
