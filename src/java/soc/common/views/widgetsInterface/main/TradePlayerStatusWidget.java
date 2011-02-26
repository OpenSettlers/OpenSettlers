package soc.common.views.widgetsInterface.main;

import soc.common.game.trading.TradeResponse;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.IsWidget;

public interface TradePlayerStatusWidget extends IsWidget
{

    public void update(TradeResponse tradeResponse);

    public void setWaiting();

}
