package soc.common.game.trading;

import soc.common.board.resources.ResourceList;

public interface TradeOffer
{
    public int getTradeOfferID();
    public ResourceList getOfferedResources();
    public ResourceList getRequestedResources();
}