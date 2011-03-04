package soc.common.lobby;

import com.google.gwt.event.shared.EventHandler;

public interface GameListChangedEventHandler extends EventHandler
{
    public void onGameListChanged(GameListChangedEvent event);
}
