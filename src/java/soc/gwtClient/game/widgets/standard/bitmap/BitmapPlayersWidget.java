package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Widget;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.AbstractPlayersWidget;
import soc.gwtClient.game.abstractWidgets.IPlayerWidget;

public class BitmapPlayersWidget extends AbstractPlayersWidget
{
    public BitmapPlayersWidget(Game game)
    {
        super(game);
    }

    @Override
    public IPlayerWidget createPlayerWidget(Game game, Player player)
    {
        return new BitmapPlayerWidget(game, player);
    }

    @Override
    public Point2D getTopRightLocation()
    {
        Widget w = rootPanel;
        return new Point2D(w.getAbsoluteLeft() + w.getOffsetWidth(), 
                w.getAbsoluteTop());
    }

    @Override
    public int getPlayerWidgetHeight()
    {
        return rootPanel.getWidget(0).getOffsetHeight();
    }

}
