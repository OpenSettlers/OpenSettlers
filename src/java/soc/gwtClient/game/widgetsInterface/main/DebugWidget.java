package soc.gwtClient.game.widgetsInterface.main;

import soc.common.actions.gameAction.meta.MessageFromServer;

import com.google.gwt.user.client.ui.IsWidget;

public interface DebugWidget extends IsWidget
{
    public void addError(MessageFromServer error);
}
