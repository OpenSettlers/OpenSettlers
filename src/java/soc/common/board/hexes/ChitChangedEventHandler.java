package soc.common.board.hexes;

import com.google.gwt.event.shared.EventHandler;

public interface ChitChangedEventHandler extends EventHandler
{
    void onChitChanged(ChitChangedEvent event);
}
