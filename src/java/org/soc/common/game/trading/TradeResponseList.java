package org.soc.common.game.trading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.player.GamePlayer;


import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class TradeResponseList implements Serializable
{
    private static final long serialVersionUID = -5258619888338776149L;
    private List<TradeResponse> tradeResponses = new ArrayList<TradeResponse>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public void addResponse(TradeResponse response)
    {
        tradeResponses.add(response);
        eventBus.fireEvent(new TradeRespondedEvent(response));
    }

    public int size()
    {
        return tradeResponses.size();
    }

    public boolean containsResponse(GamePlayer player)
    {
        for (TradeResponse response : tradeResponses)
            if (response.getPlayer().equals(player))
                return true;

        return false;
    }

    public HandlerRegistration addTradeRespondedEventHandler(
            TradeRespondedEventHandler handler)
    {
        return eventBus.addHandler(TradeRespondedEvent.TYPE, handler);
    }
}
