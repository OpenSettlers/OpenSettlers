package soc.common.game.trading;

import soc.common.board.resources.ResourceList;

public interface ITradeOffer
{
    public int getTradeOfferID();
    public ResourceList getOfferedResources();
    public ResourceList getRequestedResources();
}