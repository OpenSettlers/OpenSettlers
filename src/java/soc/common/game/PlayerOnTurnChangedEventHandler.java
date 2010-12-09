package soc.common.game;

import com.google.gwt.event.shared.EventHandler;

public interface PlayerOnTurnChangedEventHandler extends EventHandler
{
    public void onPlayerOnTurnChanged(PlayerOnTurnChangedEvent event);
}
