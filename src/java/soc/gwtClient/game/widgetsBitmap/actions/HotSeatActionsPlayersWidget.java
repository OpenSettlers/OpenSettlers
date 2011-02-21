package soc.gwtClient.game.widgetsBitmap.actions;

import java.util.HashMap;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.HandCardsWidget;
import soc.gwtClient.game.widgetsInterface.main.PlayerStuffWidget;

import com.google.gwt.user.client.ui.DeckPanel;

public class HotSeatActionsPlayersWidget extends DeckPanel implements
        PlayerStuffWidget
{
    private HashMap<GamePlayer, Integer> playersStuff = new HashMap<GamePlayer, Integer>();
    private HashMap<Integer, PlayerStuffWidget> indexedWidgets = new HashMap<Integer, PlayerStuffWidget>();
    private GameWidget gameWidget;
    private PlayerStuffWidget currentWidget;

    /**
     * @return the currentWidget
     */
    public ActionsWidget getCurrentActionsWidget()
    {
        return currentWidget.getActionsWidget();
    }

    public HotSeatActionsPlayersWidget(GameWidget gameWidget,
            GameWidgetFactory factory)
    {
        super();
        this.gameWidget = gameWidget;
        int index = 0;
        for (GamePlayer player : gameWidget.getGame().getPlayers())
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

    @Override
    public ActionsWidget getActionsWidget()
    {
        int index = playersStuff.get(gameWidget.getPlayingPlayer());
        currentWidget = indexedWidgets.get(index);
        return currentWidget.getActionsWidget();
    }

    @Override
    public Point2D getDiceWidgetTopLeftPosition()
    {
        if (playersStuff.get(gameWidget.getPlayingPlayer()) == null)
        {
            int y = 2;
            y++;
        }
        int index = playersStuff.get(gameWidget.getPlayingPlayer());
        PlayerStuffWidget widget = indexedWidgets.get(index);
        return widget.getDiceWidgetTopLeftPosition();
    }

    @Override
    public HandCardsWidget getHandCardsWidget()
    {
        int index = playersStuff.get(gameWidget.getPlayingPlayer());
        currentWidget = indexedWidgets.get(index);
        return currentWidget.getHandCardsWidget();
    }
}
