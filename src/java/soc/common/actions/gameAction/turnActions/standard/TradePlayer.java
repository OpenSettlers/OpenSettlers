package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;

public class TradePlayer extends AbstractTurnAction
{
    private static final long serialVersionUID = 1492739389253213310L;
    private ResourceList offeredResources = new ResourceList();
    private ResourceList requestedResources = new ResourceList();
    private TradeOffer originatingOffer;
    private AcceptTradeOffer acceptedOffer;
    private CounterTradeOffer acceptedCounterOffer;
    private GamePlayer tradeOpponent;

    public TradeOffer getOriginatingOffer()
    {
        return originatingOffer;
    }

    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    public ResourceList getRequestedResources()
    {
        return requestedResources;
    }

    /**
     * @return the acceptedOffer
     */
    public AcceptTradeOffer getAcceptedOffer()
    {
        return acceptedOffer;
    }

    /**
     * @return the acceptedCounterOffer
     */
    public CounterTradeOffer getAcceptedCounterOffer()
    {
        return acceptedCounterOffer;
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

        if (acceptedOffer == null && acceptedCounterOffer == null)
        {
            invalidMessage = "acceptedOffer or accptedCounterOffer cannot be both null";
            return false;
        }

        if (acceptedOffer != null && acceptedCounterOffer != null)
        {
            invalidMessage = "acceptedOffer or accptedCounterOffer cannot be both containing a response";
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

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
     * soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        tradeOpponent.getResources().moveTo(player.getResources(),
                requestedResources);
        player.getResources().moveTo(tradeOpponent.getResources(),
                offeredResources);

        // TODO: fix message

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
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
