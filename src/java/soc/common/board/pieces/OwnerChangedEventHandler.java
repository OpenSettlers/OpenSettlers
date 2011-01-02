package soc.common.board.pieces;

import com.google.gwt.event.shared.EventHandler;

public interface OwnerChangedEventHandler extends EventHandler
{
    public void onOwnerChanged(OwnerChangedEvent event);
}
