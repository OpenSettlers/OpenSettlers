package soc.common.board;

import com.google.gwt.event.shared.EventHandler;

public interface HexChangedEventHandler extends EventHandler
{
    public void onHexChanged(HexChangedEvent event);
}
