package org.soc.common.board.resources;

import com.google.gwt.event.shared.EventHandler;

public interface ResourcesChangedEventHandler extends EventHandler
{
    void onResourcesChanged(ResourcesChangedEvent resourcesChanged);
}
