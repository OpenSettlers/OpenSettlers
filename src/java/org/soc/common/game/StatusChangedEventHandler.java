package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;

public interface StatusChangedEventHandler extends EventHandler
{
    public void onStatusChanged(StatusChangedEvent event);
}
