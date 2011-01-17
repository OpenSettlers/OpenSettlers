package soc.common.game.trading;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turnActions.standard.TradeOffer;

import com.google.gwt.event.shared.SimpleEventBus;

public class TradeOfferList
{
    private List<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();
    private SimpleEventBus eventBus = new SimpleEventBus();

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
        return tradeOffers.get(tradeOffers.size() - 1);
    }

    public int size()
    {
        return tradeOffers.size();
    }
}
