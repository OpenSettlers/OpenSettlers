package org.soc.common.board.ports;

import com.google.gwt.event.shared.GwtEvent;

public class PortChangedEvent extends GwtEvent<PortChangedEventHandler>
{
    public static Type<PortChangedEventHandler> TYPE = new Type<PortChangedEventHandler>();    
    private Port port;
    
    public PortChangedEvent(Port port)
    {
        this.port=port;
    }
    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    @Override
    protected void dispatch(PortChangedEventHandler handler)
    {
        handler.onPortChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PortChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}