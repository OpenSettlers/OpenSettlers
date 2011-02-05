package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.AbstractPlayersWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerWidget;
import soc.gwtClient.game.widgets.standard.bitmap.player.PlayerBitmapWidget;

import com.google.gwt.user.client.ui.Widget;

public class PlayersBitmapWidget extends AbstractPlayersWidget
{
    public PlayersBitmapWidget(GamePanel gamePanel)
    {
        super(gamePanel);
    }

    @Override
    public PlayerWidget createPlayerWidget(GamePanel gamePanel,
            GamePlayer player)
    {
        return new PlayerBitmapWidget(gamePanel, player);
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
