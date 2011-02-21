package soc.gwtClient.game.widgetsInterface.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface PlayersInfoWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public PlayerInfoWidget createPlayerWidget(GameWidget gameWidget,
            GamePlayer player);

    public Point2D getTopRightLocation();

    public Point2D getTopRightLocation(GamePlayer player);

    public int getPlayerWidgetHeight();
}
