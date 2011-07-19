package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.trading.TradeResponse;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.IsWidget;

public interface TradePlayerStatusWidget extends IsWidget
{

    public void update(TradeResponse tradeResponse);

    public void setWaiting();

}
