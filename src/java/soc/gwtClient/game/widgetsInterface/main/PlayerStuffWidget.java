package soc.gwtClient.game.widgetsInterface.main;

import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerStuffWidget extends IsWidget
{
    public Point2D getDiceWidgetTopLeftPosition();

    public ActionsWidget getActionsWidget();

    public HandCardsWidget getHandCardsWidget();
}
