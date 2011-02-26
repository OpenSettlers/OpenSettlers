package soc.common.views.widgetsInterface.main;

import soc.common.views.widgetsInterface.actions.ActionsWidget;
import soc.common.views.widgetsInterface.generic.Point2D;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerStuffWidget extends IsWidget
{
    public Point2D getDiceWidgetTopLeftPosition();

    public ActionsWidget getActionsWidget();

    public HandCardsWidget getHandCardsWidget();
}
