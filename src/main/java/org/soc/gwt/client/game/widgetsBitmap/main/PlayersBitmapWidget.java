package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayerInfoWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractPlayersWidget;
import org.soc.gwt.client.game.widgetsBitmap.playerInfo.PlayerBitmapWidget;

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
