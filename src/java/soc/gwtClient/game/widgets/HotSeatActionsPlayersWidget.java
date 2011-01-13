package soc.gwtClient.game.widgets;

import java.util.HashMap;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.ActionsWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.widgets.abstractWidgets.PlayerStuffWidget;

import com.google.gwt.user.client.ui.DeckPanel;

public class HotSeatActionsPlayersWidget extends DeckPanel
{
    private HashMap<GamePlayer, Integer> playersStuff = new HashMap<GamePlayer, Integer>();
    private HashMap<Integer, PlayerStuffWidget> indexedWidgets = new HashMap<Integer, PlayerStuffWidget>();
    private GamePanel gamePanel;
    private PlayerStuffWidget currentWidget;

    /**
     * @return the currentWidget
     */
    public ActionsWidget getCurrentActionsWidget()
    {
        return currentWidget.getActionsWidget();
    }

    public HotSeatActionsPlayersWidget(GamePanel gamePanel,
            GameWidgetFactory factory)
    {
        super();
        this.gamePanel = gamePanel;
        int index = 0;
        for (GamePlayer player : gamePanel.getGame().getPlayers())
        {
            PlayerStuffWidget widget = factory.createPlayerStuffWidget(player);
            this.add(widget.asWidget());
            playersStuff.put(player, index);
            indexedWidgets.put(index, widget);
            if (currentWidget == null)
            {
                currentWidget = widget;
            }
            index++;
        }
        this.showWidget(0);
    }

    public void setPlayer(GamePlayer player)
    {
        int index = playersStuff.get(player);
        currentWidget = indexedWidgets.get(index);
        this.showWidget(index);
    }

    public Point2D getTopLeftDiceWidgetPosition(GamePlayer player)
    {
        int index = playersStuff.get(player);
        PlayerStuffWidget widget = indexedWidgets.get(index);
        return widget.getDiceWidgetTopLeftPosition();
    }
}
