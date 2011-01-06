package soc.gwtClient.game.widgets.standard.bitmap;

import java.util.HashMap;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.OrderChangedEvent;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.AbstractPlayersWidget;
import soc.gwtClient.game.abstractWidgets.PlayerWidget;
import soc.gwtClient.game.widgets.standard.bitmap.player.PlayerBitmapWidget;

import com.google.gwt.user.client.ui.Widget;

public class PlayersBitmapWidget extends AbstractPlayersWidget
{
    public PlayersBitmapWidget(Game game)
    {
        super(game);
    }

    @Override
    public PlayerWidget createPlayerWidget(Game game, GamePlayer player)
    {
        return new PlayerBitmapWidget(game, player);
    }

    @Override
    public Point2D getTopRightLocation()
    {
        Widget w = rootPanel;
        return new Point2D(w.getAbsoluteLeft() + w.getOffsetWidth(), w
                .getAbsoluteTop());
    }

    @Override
    public int getPlayerWidgetHeight()
    {
        return rootPanel.getWidget(0).getOffsetHeight();
    }

    @Override
    public void onOrderChanged(OrderChangedEvent event)
    {
        HashMap<GamePlayer, PlayerWidget> playersWidgets = new HashMap<GamePlayer, PlayerWidget>();
        for (int i = 0; i < rootPanel.getWidgetCount(); i++)
        {
            PlayerWidget widget = (PlayerWidget) rootPanel.getWidget(i);
            playersWidgets.put(widget.getPlayer(), widget);
        }

        rootPanel.clear();

        for (GamePlayer player : game.getPlayers())
        {
            rootPanel.add(playersWidgets.get(player));
        }
    }
}
