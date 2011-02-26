package soc.gwtClient.game.widgetsBitmap.main;

import java.util.HashMap;
import java.util.Map;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.main.HotSeatTradePlayersWidget;
import soc.gwtClient.game.widgetsBitmap.dialogs.TradePlayersDialog;

public class HotSeatTradePlayersWidgetImpl implements HotSeatTradePlayersWidget
{
    private Map<GamePlayer, TradePlayerDialog> playersTradeUI = new HashMap<GamePlayer, TradePlayerDialog>();
    private GameWidget gameWidget;

    public HotSeatTradePlayersWidgetImpl(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        for (GamePlayer player : gameWidget.getGame().getPlayers())
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
