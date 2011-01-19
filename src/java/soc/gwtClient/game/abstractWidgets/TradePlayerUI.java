package soc.gwtClient.game.abstractWidgets;

import soc.common.game.trading.TradeResponse;

import com.google.gwt.user.client.ui.IsWidget;

public interface TradePlayerUI extends IsWidget
{
    public void show();

    public void trade(TradeResponse tradeResponse);
}
