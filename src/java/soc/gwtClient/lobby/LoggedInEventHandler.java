package soc.gwtClient.lobby;

import com.google.gwt.event.shared.EventHandler;

public interface LoggedInEventHandler extends EventHandler
{
    public void onLoggedIn(LoggedInEvent event);
}
