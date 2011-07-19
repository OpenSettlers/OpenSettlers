package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.HandCardsWidget;

public interface HotSeatActionsPlayersWidget
{
    public ActionsWidget getCurrentActionsWidget();

    public void setPlayer(GamePlayer player);

    public ActionsWidget getActionsWidget();

    public Point2D getDiceWidgetTopLeftPosition();

    public HandCardsWidget getHandCardsWidget();
}
