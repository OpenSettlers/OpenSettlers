package soc.gwtClient.game.widgets.abstractWidgets;

import soc.gwtClient.game.behaviour.RollDiceResult;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealCardWidget extends IsWidget
{
    public void update(RollDiceResult result);
}
