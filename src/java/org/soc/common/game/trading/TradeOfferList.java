package org.soc.common.game.trading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.actions.gameAction.trading.TradeOffer;


import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class TradeOfferList implements Serializable
{
    private static final long serialVersionUID = 279721035013362133L;
    private List<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public void addOffer(TradeOffer tradeOffer)
    {
        tradeOffers.add(tradeOffer);
        tradeOffer.setID(tradeOffers.size());
        eventBus.fireEvent(new TradeOfferedEvent(tradeOffer));
    }

    public TradeOffer getByID(int id)
    {
        for (TradeOffer offer : tradeOffers)
            if (offer.getID() == id)
                return offer;

        return null;
    }

    public TradeOffer getLatestOffer()
    {
        return tradeOffers.size() == 0 ? null : tradeOffers.get(tradeOffers
                .size() - 1);
    }

    public int size()
    {
        return tradeOffers.size();
    }

    public HandlerRegistration addTradeOfferedEventHandler(
            TradeOfferedEventHandler handler)
    {
        return eventBus.addHandler(TradeOfferedEvent.TYPE, handler);
    }
}
