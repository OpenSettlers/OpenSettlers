package soc.common.actions.gameAction.trading;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponseList;
import soc.common.server.gameActions.GameServerActionFactory;
import soc.common.server.gameActions.ServerAction;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwt.client.images.Resources;

public class TradeOffer extends AbstractGameAction
{
    private static final long serialVersionUID = 3603265436041339994L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().trade16(), Resources
                        .icons().trade32(), Resources.icons().trade48());

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
    private TradeResponseList responses = new TradeResponseList();
    private boolean responsesCompleted = false;
    private int offerID;

    public TradeResponseList getResponses()
    {
        return responses;
    }

    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    public ResourceList getRequestedResources()
    {
        return requestedResources;
    }

    public int getOfferID()
    {
        return offerID;
    }

    public void setOfferID(int id)
    {
        offerID = id;
    }

    /** @return the responsesCompleted */
    public boolean isResponsesCompleted()
    {
        return responsesCompleted;
    }

    /** @param responsesCompleted
     *            the responsesCompleted to set */
    public TradeOffer setResponsesCompleted(boolean responsesCompleted)
    {
        this.responsesCompleted = responsesCompleted;
        return this;
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

        if (!player.getResources().hasAtLeast(offeredResources))
        {
            invalidMessage = "Player does not have offered resources";
            return false;
        }

        if (game.getCurrentTurn().getTradeOffers().size() >= game
                        .getGameSettings().getMaximumTradesPerTurn()
                        .getMaxTradesPerTurn())
        {
            invalidMessage = "Already used all possible trade offers";
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
        game.getCurrentTurn().getTradeOffers().addOffer(this);

        for (GamePlayer player : game.getPlayers())
            game.getActionsQueue().enqueue(
                            new QueuedTradeResponse().setPlayer(player), false);

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        // TODO Auto-generated method stub
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
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createTradePlayerWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createTradeOfferBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createTradeOfferBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createTradeOfferBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createTradeOfferBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#createServerAction(soc
     * .common.server.actions.ServerActionFactory)
     */
    @Override
    public ServerAction createServerAction(GameServerActionFactory factory)
    {
        return factory.createTradeOfferAction(this);
    }
}