package soc.gwtClient.game.widgets.abstractWidgets;

import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.ActionsWidget;
import soc.gwtClient.game.abstractWidgets.HandCardsWidget;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerStuffWidget extends IsWidget
{
    public Point2D getDiceWidgetTopLeftPosition();

    public ActionsWidget getActionsWidget();

    public HandCardsWidget getHandCardsWidget();
}
