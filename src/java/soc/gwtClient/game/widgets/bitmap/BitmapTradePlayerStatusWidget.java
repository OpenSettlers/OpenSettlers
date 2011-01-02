package soc.gwtClient.game.widgets.bitmap;

import soc.common.game.GamePlayer;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractTradePlayerStatusWidget;

public class BitmapTradePlayerStatusWidget extends
        AbstractTradePlayerStatusWidget
{

    public BitmapTradePlayerStatusWidget(IGamePanel gamePanel, GamePlayer opponent, GamePlayer playingPlayer)
    {
        super(gamePanel, opponent, playingPlayer);
    }

}
