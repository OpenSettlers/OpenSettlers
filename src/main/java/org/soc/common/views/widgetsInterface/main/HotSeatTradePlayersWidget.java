package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;

public interface HotSeatTradePlayersWidget
{
    public TradePlayerDialog getPlayerTradeWidget(GamePlayer player);
}
