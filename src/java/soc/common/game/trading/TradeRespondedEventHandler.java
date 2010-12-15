package soc.common.game.trading;

import com.google.gwt.event.shared.EventHandler;

public interface TradeRespondedEventHandler extends EventHandler
{
    public void onTradeResponded(TradeRespondedEvent event);
}
