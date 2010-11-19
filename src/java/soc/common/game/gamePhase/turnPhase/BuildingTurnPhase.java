package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.message.EndTurn;

public class BuildingTurnPhase extends TurnPhase
{
    private TradingTurnPhase tradingTurnPhase;
    
    public BuildingTurnPhase(TradingTurnPhase tradingTurnPhase)
    {
        this.tradingTurnPhase = tradingTurnPhase; 
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        // TODO Auto-generated method stub
        return super.next();
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#processAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public TurnPhase processAction(GameAction action, Game game)
    {
            action.perform(game);

            //EndTurn endTurn = action as EndTurnAction;
            if (action instanceof EndTurn)
            {
                return new BeforeDiceRollTurnPhase();
            }
            
            return this;
        }
        else
        {
            // Check if we are allowed to trade
            TradeOfferAction tradeOffer = action as TradeOfferAction;
            if (tradeOffer != null)
            {
                // Only when game setting allows it
                if (game.Settings.TradingAfterBuilding)
                {
                    return _TradingPhase;
                }
                return null;
            }
            return null;
        }
    }

}
