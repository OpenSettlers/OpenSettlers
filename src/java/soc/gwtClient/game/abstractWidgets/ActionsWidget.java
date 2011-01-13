package soc.gwtClient.game.abstractWidgets;

import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.factories.ActionWidgetFactory;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface ActionsWidget extends IsWidget
{
    public ActionWidgetFactory getActionWidgetFactory();

    public ComplexPanel createRootPanel();

    public Point2D getTopLeftDiceWidgetPosition();

    public void setEnabled(boolean enabled);
}
