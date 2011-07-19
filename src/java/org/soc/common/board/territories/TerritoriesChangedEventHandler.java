package org.soc.common.board.territories;

import com.google.gwt.event.shared.EventHandler;

public interface TerritoriesChangedEventHandler extends EventHandler
{
    public void onTerritoriesChanged(TerritoriesChangedEvent event);
}
