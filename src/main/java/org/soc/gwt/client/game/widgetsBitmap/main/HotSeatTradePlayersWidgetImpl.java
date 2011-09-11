package org.soc.gwt.client.game.widgetsBitmap.main;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.HotSeatTradePlayersWidget;
import org.soc.gwt.client.game.widgetsBitmap.dialogs.TradePlayersDialog;


public class HotSeatTradePlayersWidgetImpl implements HotSeatTradePlayersWidget
{
    private Map<GamePlayer, TradePlayerDialog> playersTradeUI = new HashMap<GamePlayer, TradePlayerDialog>();
    private GameWidget gameWidget;

    public HotSeatTradePlayersWidgetImpl(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        for (GamePlayer player : gameWidget.game().players())
        {
            TradePlayerDialog tradeWidget = new TradePlayersDialog(gameWidget,
                    player);
            playersTradeUI.put(player, tradeWidget);
        }
    }

    public TradePlayerDialog getPlayerTradeWidget(GamePlayer player)
    {
        return playersTradeUI.get(player);
    }
}
