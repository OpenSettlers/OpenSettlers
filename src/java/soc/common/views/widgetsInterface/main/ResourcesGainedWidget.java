package soc.common.views.widgetsInterface.main;

import soc.common.views.behaviour.gameWidget.received.RollDiceResult;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Shows a popup panel with a list of resources gained by each player
 * due to a rolled dice
 */
public interface ResourcesGainedWidget extends IsWidget
{
    public void update(RollDiceResult rollDiceResult);

    public void show();
}
