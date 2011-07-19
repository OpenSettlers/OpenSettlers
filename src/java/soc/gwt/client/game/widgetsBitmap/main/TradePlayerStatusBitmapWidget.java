package soc.gwt.client.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.main.AbstractTradePlayerStatusWidget;

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
