package soc.gwtClient.game.widgets.bitmap;

import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractTradePlayerStatusWidget;

public class BitmapTradePlayerStatusWidget extends
        AbstractTradePlayerStatusWidget
{

    public BitmapTradePlayerStatusWidget(IGamePanel gamePanel, Player opponent, Player playingPlayer)
    {
        super(gamePanel, opponent, playingPlayer);
    }

}
