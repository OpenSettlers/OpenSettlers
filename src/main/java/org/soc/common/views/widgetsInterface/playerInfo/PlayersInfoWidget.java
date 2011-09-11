package org.soc.common.views.widgetsInterface.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;

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
