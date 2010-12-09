package soc.common.game;

import com.google.gwt.event.shared.EventHandler;

public interface GamePhaseChangedEventHandler extends EventHandler
{
    public void onGamePhaseChanged(GamePhaseChangedEvent event);
}
