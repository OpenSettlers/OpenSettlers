package soc.gwtClient.game.widgetsBitmap.actions;

import java.util.HashMap;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.PlayerStuffWidget;

import com.google.gwt.user.client.ui.DeckPanel;

public class HotSeatActionsPlayersWidget extends DeckPanel
{
    private HashMap<GamePlayer, Integer> playersStuff = new HashMap<GamePlayer, Integer>();
    private HashMap<Integer, PlayerStuffWidget> indexedWidgets = new HashMap<Integer, PlayerStuffWidget>();
    private GameWidget gamePanel;
    private PlayerStuffWidget currentWidget;

    /**
     * @return the currentWidget
     */
    public ActionsWidget getCurrentActionsWidget()
    {
        return currentWidget.getActionsWidget();
    }

    public HotSeatActionsPlayersWidget(GameWidget gamePanel,
            GameWidgetFactory factory)
    {
        super();
        this.gamePanel = gamePanel;
        int index = 0;
        for (GamePlayer player : gamePanel.getGame().getPlayers())
        {
            if (player.getBot() == null)
            {
                PlayerStuffWidget widget = factory
                        .createPlayerStuffWidget(player);
                this.add(widget.asWidget());
                playersStuff.put(player, index);
                indexedWidgets.put(index, widget);
                if (currentWidget == null)
                {
                    currentWidget = widget;
                }
                index++;
            }
        }

        // Only show first widget when present, bot-only games don't need player
        // widgets
        if (playersStuff.size() > 0)
            this.showWidget(0);
    }

    public void setPlayer(GamePlayer player)
    {
        if (playersStuff.size() > 0)
        {
            int index = playersStuff.get(player);
            currentWidget = indexedWidgets.get(index);
            this.showWidget(index);
        }
    }

    public Point2D getTopLeftDiceWidgetPosition(GamePlayer player)
    {
        int index = playersStuff.get(player);
        PlayerStuffWidget widget = indexedWidgets.get(index);
        return widget.getDiceWidgetTopLeftPosition();
    }
}
