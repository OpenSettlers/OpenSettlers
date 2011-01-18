package soc.common.game.trading;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class TradeResponseList
{
    private List<TradeResponse> tradeResponses = new ArrayList<TradeResponse>();
    private SimpleEventBus eventBus = new SimpleEventBus();

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
