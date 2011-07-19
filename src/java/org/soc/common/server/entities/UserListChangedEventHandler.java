package org.soc.common.server.entities;

import com.google.gwt.event.shared.EventHandler;

public interface UserListChangedEventHandler extends EventHandler
{
    public void onUserListChanged(UserListChangedEvent event);
}
