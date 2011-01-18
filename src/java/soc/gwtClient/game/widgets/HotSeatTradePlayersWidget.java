package soc.gwtClient.game.widgets;

import java.util.HashMap;
import java.util.Map;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.TradePlayerUI;
import soc.gwtClient.game.dialogs.TradePlayersDialog;

public class HotSeatTradePlayersWidget
{
    private Map<GamePlayer, TradePlayerUI> playersTradeUI = new HashMap<GamePlayer, TradePlayerUI>();
    private GamePanel gamePanel;

    public HotSeatTradePlayersWidget(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        for (GamePlayer player : gamePanel.getGame().getPlayers())
        {
            TradePlayerUI tradeWidget = new TradePlayersDialog(gamePanel,
                    player);
            playersTradeUI.put(player, tradeWidget);
        }
    }

    public TradePlayerUI getPlayerTradeUI(GamePlayer player)
    {
        return playersTradeUI.get(player);
    }
}
