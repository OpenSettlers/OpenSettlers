package org.soc.common.game.logs;

import com.google.gwt.event.shared.EventHandler;

public interface ActionPerformedEventHandler extends EventHandler
{
    public void onActionPerformed(ActionPerformedEvent event);
}
