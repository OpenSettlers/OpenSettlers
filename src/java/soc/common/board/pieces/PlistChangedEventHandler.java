package soc.common.board.pieces;

import com.google.gwt.event.shared.EventHandler;

public interface PlistChangedEventHandler<P> extends EventHandler
{
    public void onPlistChanged(PlistChangedEvent<P> event);
}
