package org.soc.common.views.widgetsInterface.main;

import org.soc.common.actions.gameAction.meta.MessageFromServer;

import com.google.gwt.user.client.ui.IsWidget;

public interface DebugWidget extends IsWidget
{
    public void addError(MessageFromServer error);
}
