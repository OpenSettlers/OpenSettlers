package org.soc.common.game.trading;

import com.google.gwt.event.shared.EventHandler;

public interface TradeOfferedEventHandler extends EventHandler
{
    public void onTradeOffered(TradeOfferedEvent event);
}
