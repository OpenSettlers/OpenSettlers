package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.actions.gameAction.MessageFromServer;

import com.google.gwt.user.client.ui.IsWidget;

public interface DebugWidget extends IsWidget
{
    public void addError(MessageFromServer error);
}
