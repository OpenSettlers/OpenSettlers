package soc.gwtClient.game.widgets.bitmap;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.TradePlayerUI;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractTradePlayerStatusWidget;

public class TradePlayerStatusBitmapWidget extends
        AbstractTradePlayerStatusWidget
{

    public TradePlayerStatusBitmapWidget(GamePanel gamePanel,
            GamePlayer opponent, GamePlayer playingPlayer,
            TradePlayerUI tradePlayerUI)
    {
        super(gamePanel, opponent, playingPlayer, tradePlayerUI);
    }

}
