package soc.common.views.widgetsInterface.main;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;

public interface HotSeatTradePlayersWidget
{
    public TradePlayerDialog getPlayerTradeWidget(GamePlayer player);
}
