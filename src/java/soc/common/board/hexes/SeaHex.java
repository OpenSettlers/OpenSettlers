package soc.common.board.hexes;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import soc.common.board.ports.Port;
import soc.common.board.ports.PortChangedEvent;
import soc.common.board.ports.PortChangedEventHandler;

@SuppressWarnings("deprecation")
public class SeaHex extends Hex implements HasHandlers
{
    /*
     * A SeaHex may have a port associated with it
     */
    private Port port;
    private HandlerManager handlerManager = new HandlerManager(this);

    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public SeaHex setPort(Port p)
    {
        this.port = p;
        
        fireEvent(new PortChangedEvent(port));
        
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        return new SeaHex()
            .setLocation(hexLocation);
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkBlue";
    }

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }
    
    public HandlerRegistration addPortChangedEventHandler(PortChangedEventHandler handler)
    {
        return handlerManager.addHandler(PortChangedEvent.TYPE, handler);
    }
}
