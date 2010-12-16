package soc.common.game;

import soc.common.game.trading.TradeOfferList;

public class Turn implements ITurn
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

    @Override
    public ITurn setID(int id)
    {
        this.id=id;
        return this;
    }
    
}
