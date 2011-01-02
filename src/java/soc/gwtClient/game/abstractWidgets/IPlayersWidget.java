package soc.gwtClient.game.abstractWidgets;

import soc.common.game.Game;
import soc.common.game.GamePlayer;
import soc.gwtClient.game.Point2D;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IPlayersWidget extends IsWidget
{
    public ComplexPanel createRootPanel();
    public IPlayerWidget createPlayerWidget(Game game, GamePlayer player);
    public Point2D getTopRightLocation();
    public int getPlayerWidgetHeight();
}
