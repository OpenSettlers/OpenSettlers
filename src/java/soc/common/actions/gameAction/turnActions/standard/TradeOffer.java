package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.QueuedTradeResponse;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponseList;

public class TradeOffer extends AbstractGameAction
{
    private static final long serialVersionUID = 3603265436041339994L;
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

    /**
     * @return the responsesCompleted
     */
    public boolean isResponsesCompleted()
    {
        return responsesCompleted;
    }

    /**
     * @param responsesCompleted
     *            the responsesCompleted to set
     */
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
                .getGameSettings().getMaximumTradesPerTurn())
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
        return turnPhase instanceof TradingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }
}