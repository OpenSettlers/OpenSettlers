package soc.common.board.ports;


import com.google.gwt.event.shared.EventHandler;

public interface PortChangedEventHandler extends EventHandler
{
    public void onPortChanged(PortChangedEvent event);
}
