package soc.common.game;

import soc.common.game.trading.TradeOfferList;

public interface ITurn
{
    public TradeOfferList getTradeOffers();
    public Player getPlayer();
    public int getID();
    public ITurn setID(int id);
}
