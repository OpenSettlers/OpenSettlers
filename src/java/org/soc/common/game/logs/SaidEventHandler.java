package org.soc.common.game.logs;

import com.google.gwt.event.shared.EventHandler;

public interface SaidEventHandler extends EventHandler
{
    public void onSaid(SaidEvent event);
}
