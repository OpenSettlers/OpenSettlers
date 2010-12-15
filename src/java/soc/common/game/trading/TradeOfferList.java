package soc.common.game.trading;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

public class TradeOfferList
{
    private List<ITradeOffer> tradeOffers = new ArrayList<ITradeOffer>();
    private SimpleEventBus eventBus = new SimpleEventBus();
    
    public void addOffer(ITradeOffer tradeOffer)
    {
        tradeOffers.add(tradeOffer);
        eventBus.fireEvent(new TradeOfferedEvent(tradeOffer));
    }
    public ITradeOffer getLatestTrade()
    {
        return tradeOffers.get(tradeOffers.size()-1);
    }
}
