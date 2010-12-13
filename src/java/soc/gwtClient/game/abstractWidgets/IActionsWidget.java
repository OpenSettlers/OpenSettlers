package soc.gwtClient.game.abstractWidgets;

import soc.gwtClient.game.abstractWidgets.factories.IActionWidgetFactory;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IActionsWidget extends IsWidget
{
    public IActionWidgetFactory getActionWidgetFactory();
    public ComplexPanel createRootPanel();
}
