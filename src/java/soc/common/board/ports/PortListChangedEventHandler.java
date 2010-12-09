package soc.common.board.ports;

import com.google.gwt.event.shared.EventHandler;

public interface PortListChangedEventHandler extends EventHandler
{
    public void onPortsChanged(PortListChangedEvent event);
}
