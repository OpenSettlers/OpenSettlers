package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;

public interface VictoryPointsChangedEventHandler extends EventHandler
{
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event);
}
