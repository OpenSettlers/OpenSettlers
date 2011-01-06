package soc.gwtClient.game.abstractWidgets;

import soc.common.game.VictoryPointsChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface VictoryPointsWidget extends IsWidget, VictoryPointsChangedEventHandler
{
    public ComplexPanel createRootPanel();
}
