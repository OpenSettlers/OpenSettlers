package soc.common.game;

import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.trading.TradeOfferList;

public class TurnImpl implements Turn
{
    private Player player;
    private int id;
    private TradeOfferList tradeOffers;
    
    @Override
    public TradeOfferList getTradeOffers()
    {
        return tradeOffers;
    }

    /**
     * @return the player
     */
    public Player getPlayer()
    {
        return player;
    }

    @Override
    public int getID()
    {
        return id;
    }

    public Turn setID(int id)
    {
        this.id=id;
        return this;
    }

    @Override
    public TurnPhase getTurnPhase()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
