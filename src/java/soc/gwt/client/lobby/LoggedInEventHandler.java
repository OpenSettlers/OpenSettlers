package soc.gwt.client.lobby;

import com.google.gwt.event.shared.EventHandler;

public interface LoggedInEventHandler extends EventHandler
{
    public void onLoggedIn(LoggedInEvent event);
}
