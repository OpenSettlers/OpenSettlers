package soc.common.actions.gameAction.turnActions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.trading.TradeResponse;

public class QueuedTradeResponse extends AbstractGameAction implements
        TradeResponse
{
    private static final long serialVersionUID = 919585819869290645L;

    @Override
    public TradeOffer getOriginatingOffer()
    {
        return null;
    }

    @Override
    public TradeResponse setOriginatingOffer(TradeOffer tradeOffer)
    {
        return null;
    }

    @Override
    public void setTradeResources(TradePlayer tradePlayer)
    {
    }

    @Override
    public String getToDoMessage()
    {
        return "Respond on the trade";
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return false;
    }

}
