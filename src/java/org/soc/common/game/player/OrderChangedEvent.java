package org.soc.common.game.player;

import com.google.gwt.event.shared.GwtEvent;

public class OrderChangedEvent extends GwtEvent<OrderChangedEventHandler>
{
    public static Type<OrderChangedEventHandler> TYPE = new Type<OrderChangedEventHandler>();

    @Override
    protected void dispatch(OrderChangedEventHandler handler)
    {
        handler.onOrderChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<OrderChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
