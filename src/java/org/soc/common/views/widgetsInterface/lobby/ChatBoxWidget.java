package org.soc.common.views.widgetsInterface.lobby;

import org.soc.common.actions.lobby.LobbyChat;

import com.google.gwt.user.client.ui.IsWidget;

public interface ChatBoxWidget extends IsWidget
{
    public void addMessage(LobbyChat chat);
}
