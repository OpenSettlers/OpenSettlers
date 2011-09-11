package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractTradePlayerStatusWidget;

public class TradePlayerStatusBitmapWidget extends
        AbstractTradePlayerStatusWidget
{

    public TradePlayerStatusBitmapWidget(GameWidget gameWidget,
            GamePlayer opponent, GamePlayer playingPlayer,
            TradePlayerDialog tradePlayerUI)
    {
        super(gameWidget, opponent, playingPlayer, tradePlayerUI);
    }

}
