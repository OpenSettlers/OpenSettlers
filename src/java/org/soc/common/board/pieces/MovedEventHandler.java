package org.soc.common.board.pieces;

import com.google.gwt.event.shared.EventHandler;

public interface MovedEventHandler extends EventHandler
{
    public void onMoved(MovedEvent event);
}
