package org.soc.common.game.trading;

import java.io.*;
import java.util.*;

import com.google.gwt.event.shared.*;

public class TradeOfferList implements Serializable {
  private List<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  public void addOffer(TradeOffer tradeOffer) {
    tradeOffers.add(tradeOffer);
    tradeOffer.setId(tradeOffers.size());
    eventBus.fireEvent(new TradeOfferedEvent(tradeOffer));
  }
  public TradeOffer getByID(int id) {
    for (TradeOffer offer : tradeOffers)
      if (offer.id() == id)
        return offer;
    return null;
  }
  public TradeOffer getLatestOffer() {
    return tradeOffers.size() == 0 ? null : tradeOffers.get(tradeOffers
            .size() - 1);
  }
  public int size() {
    return tradeOffers.size();
  }
  public HandlerRegistration addTradeOfferedEventHandler(TradeOfferedEventHandler handler) {
    return eventBus.addHandler(TradeOfferedEvent.TYPE, handler);
  }
}
