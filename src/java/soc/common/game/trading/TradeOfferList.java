package soc.common.game.trading;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

public class TradeOfferList
{
    private List<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();
    private SimpleEventBus eventBus = new SimpleEventBus();
    
    public void addOffer(TradeOffer tradeOffer)
    {
        tradeOffers.add(tradeOffer);
        eventBus.fireEvent(new TradeOfferedEvent(tradeOffer));
    }
    public TradeOffer getLatestTrade()
    {
        return tradeOffers.get(tradeOffers.size()-1);
    }
}
