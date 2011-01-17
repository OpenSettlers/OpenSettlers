package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.trading.TradeResponse;

public class RejectTradeOffer extends AbstractGameAction implements
        TradeResponse
{
    private static final long serialVersionUID = 3831905224550592802L;
    private TradeOffer originatingOffer;

    @Override
    public TradeOffer getOriginatingOffer()
    {
        return originatingOffer;
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
        return "";
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
