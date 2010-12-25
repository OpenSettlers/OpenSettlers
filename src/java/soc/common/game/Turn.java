package soc.common.game;

import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.trading.TradeOfferList;

public interface Turn
{
    public TradeOfferList getTradeOffers();
    public Player getPlayer();
    public int getID();
    public TurnPhase getTurnPhase();
}
