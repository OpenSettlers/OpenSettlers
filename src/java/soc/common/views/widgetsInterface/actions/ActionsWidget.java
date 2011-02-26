package soc.common.views.widgetsInterface.actions;

import soc.common.views.widgetsInterface.generic.Point2D;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface ActionsWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public Point2D getTopLeftDiceWidgetPosition();

    public void setEnabled(boolean enabled);
}
