package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface PlayersWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public PlayerWidget createPlayerWidget(GamePanel gamePanel,
            GamePlayer player);

    public Point2D getTopRightLocation();

    public Point2D getTopRightLocation(GamePlayer player);

    public int getPlayerWidgetHeight();
}
