package org.soc.common.game.trading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.GamePlayer;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/** A list of responses on a trade offer */
public class TradeResponseList implements Serializable {
  private List<TradeResponse> tradeResponses = new ArrayList<TradeResponse>();
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  public void addResponse(TradeResponse response) {
    tradeResponses.add(response);
    eventBus.fireEvent(new TradeRespondedEvent(response));
  }
  public int size() {
    return tradeResponses.size();
  }
  public boolean containsResponse(GamePlayer player) {
    for (TradeResponse response : tradeResponses)
      if (response.player().equals(player))
        return true;
    return false;
  }
  public HandlerRegistration addTradeRespondedEventHandler(TradeRespondedEventHandler handler) {
    return eventBus.addHandler(TradeRespondedEvent.TYPE, handler);
  }
}
