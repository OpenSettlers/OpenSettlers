package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractTradePlayerStatusWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class TradePlayerStatusBitmapWidget extends
        AbstractTradePlayerStatusWidget
{

    public TradePlayerStatusBitmapWidget(GameWidget gamePanel,
            GamePlayer opponent, GamePlayer playingPlayer,
            TradePlayerDialog tradePlayerUI)
    {
        super(gamePanel, opponent, playingPlayer, tradePlayerUI);
    }

}
