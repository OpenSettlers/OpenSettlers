package soc.common.server;

import com.google.gwt.event.shared.EventHandler;

public interface LobbyActionEventHandler extends EventHandler
{
    public void onLobbyAction(LobbyActionEvent event);
}
