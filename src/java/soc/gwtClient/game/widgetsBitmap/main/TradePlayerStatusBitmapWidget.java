package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.main.AbstractTradePlayerStatusWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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
