package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;

public interface TurnChangedEventHandler extends EventHandler
{
    public void onTurnChanged(TurnChangedEvent event);
}
