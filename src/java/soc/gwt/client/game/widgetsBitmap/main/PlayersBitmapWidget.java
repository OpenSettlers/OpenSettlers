package soc.gwt.client.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayerInfoWidget;
import soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractPlayersWidget;
import soc.gwt.client.game.widgetsBitmap.playerInfo.PlayerBitmapWidget;

import com.google.gwt.user.client.ui.Widget;

public class PlayersBitmapWidget extends AbstractPlayersWidget
{
    public PlayersBitmapWidget(GameWidget gameWidget)
    {
        super(gameWidget);
    }

    @Override
    public PlayerInfoWidget createPlayerWidget(GameWidget gameWidget,
            GamePlayer player)
    {
        return new PlayerBitmapWidget(gameWidget, player);
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
    public Point2D getTopRightLocation(GamePlayer player)
    {
        return playersWidgets.get(player).getTopRightLocation();
    }
}
