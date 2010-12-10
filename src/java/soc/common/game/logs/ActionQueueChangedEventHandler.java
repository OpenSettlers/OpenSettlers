package soc.common.game.logs;

import com.google.gwt.event.shared.EventHandler;

public interface ActionQueueChangedEventHandler extends EventHandler
{
    public void onQueueChanged(ActionQueueChangedEvent event);
}
