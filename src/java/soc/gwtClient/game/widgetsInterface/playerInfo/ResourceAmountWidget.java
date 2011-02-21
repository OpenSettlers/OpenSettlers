package soc.gwtClient.game.widgetsInterface.playerInfo;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

/*
 * Widget to show the amount of resources a player currently holds in his hand
 */
public interface ResourceAmountWidget extends IsWidget
{
    public ComplexPanel createRootPanel();
}
