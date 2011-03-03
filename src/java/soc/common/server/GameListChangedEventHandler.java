package soc.common.server;

import com.google.gwt.event.shared.EventHandler;

public interface GameListChangedEventHandler extends EventHandler
{
    public void onGameListChanged(GameListChangedEvent event);
}
