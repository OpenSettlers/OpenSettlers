package soc.gwtClient.game.widgets.abstractWidgets;

import com.google.gwt.event.shared.EventHandler;

public interface ResourceClickedEventHandler extends EventHandler
{
    public void onResourceClicked(ResourceClickedEvent event);
}
