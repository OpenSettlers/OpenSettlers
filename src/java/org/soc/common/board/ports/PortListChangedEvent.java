package org.soc.common.board.ports;

import com.google.gwt.event.shared.GwtEvent;

public class PortListChangedEvent extends GwtEvent<PortListChangedEventHandler>
{
    public static Type<PortListChangedEventHandler> TYPE = new Type<PortListChangedEventHandler>();
    private Port addedPort;
    private Port removedPort;
    
    public PortListChangedEvent(Port addedPort, Port removedPort)
    {
        super();
        this.addedPort = addedPort;
        this.removedPort = removedPort;
    }

    /**
     * @return the addedPort
     */
    public Port getAddedPort()
    {
        return addedPort;
    }

    /**
     * @return the removedPort
     */
    public Port getRemovedPort()
    {
        return removedPort;
    }

    @Override
    protected void dispatch(PortListChangedEventHandler handler)
    {
        handler.onPortsChanged(this);
    }

    @Override
    public Type<PortListChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
