package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.trading.TradeResponse;

public class CounterTradeOffer extends AbstractGameAction implements
        TradeResponse
{
    private static final long serialVersionUID = 7506550189521999145L;
    private ResourceList offeredResources = new ResourceList();
    private ResourceList requestedResources = new ResourceList();
    private TradeOffer originatingOffer;
    private int offerID;

    /**
     * @return the offeredResources
     */
    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    /**
     * @return the requestedResources
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
        TradeOffer offer = game.getCurrentTurn().getTradeOffers().getByID(
                originatingOffer.getID());
        offer.getResponses().addResponse(this);

        // TODO: fix message

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

    @Override
    public TradeOffer getOriginatingOffer()
    {
        return originatingOffer;
    }

    public void setOriginatingOffer(TradeOffer tradeOffer)
    {
        this.originatingOffer = tradeOffer;
    }
}
