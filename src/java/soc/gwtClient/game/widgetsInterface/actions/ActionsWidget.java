package soc.gwtClient.game.widgetsInterface.actions;

import soc.gwtClient.game.Point2D;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface ActionsWidget extends IsWidget
{
    public ActionWidgetFactory getActionWidgetFactory();

    public ComplexPanel createRootPanel();

    public Point2D getTopLeftDiceWidgetPosition();

    public void setEnabled(boolean enabled);
}
