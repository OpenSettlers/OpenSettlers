package soc.common.views.widgetsInterface.dialogs;

import soc.common.game.trading.TradeResponse;

import com.google.gwt.user.client.ui.IsWidget;

public interface TradePlayerDialog extends IsWidget
{
    public void show();

    public void trade(TradeResponse tradeResponse);
}
