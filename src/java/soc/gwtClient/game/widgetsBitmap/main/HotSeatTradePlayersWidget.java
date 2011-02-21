package soc.gwtClient.game.widgetsBitmap.main;

import java.util.HashMap;
import java.util.Map;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsBitmap.dialogs.TradePlayersDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class HotSeatTradePlayersWidget
{
    private Map<GamePlayer, TradePlayerDialog> playersTradeUI = new HashMap<GamePlayer, TradePlayerDialog>();
    private GameWidget gameWidget;

    public HotSeatTradePlayersWidget(GameWidget gameWidget)
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

    public TradePlayerDialog getPlayerTradeUI(GamePlayer player)
    {
        return playersTradeUI.get(player);
    }
}
