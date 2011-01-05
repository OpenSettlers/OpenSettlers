package soc.common.game.player;

import com.google.gwt.event.shared.EventHandler;

public interface OrderChangedEventHandler extends EventHandler
{
    public void onOrderChanged(OrderChangedEvent event);
}
