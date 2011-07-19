package org.soc.common.board.hexes;

import com.google.gwt.event.shared.EventHandler;

public interface TerritoryChangedEventHandler extends EventHandler
{
    void onTerritoryChanged(TerritoryChangedEvent event);
}
