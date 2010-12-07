package soc.common.board.hexes;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.board.ports.Port;
import soc.common.board.ports.PortChangedEvent;
import soc.common.board.ports.PortChangedEventHandler;

public class SeaHex extends Hex
{
    // A SeaHex may have a port associated with it
    private Port port;
    
    // Event notification instance
    private SimpleEventBus eventBus = new SimpleEventBus();
    
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
        
        eventBus.fireEvent(new PortChangedEvent(port));
        
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

    public HandlerRegistration addPortChangedEventHandler(PortChangedEventHandler handler)
    {
        return eventBus.addHandler(PortChangedEvent.TYPE, handler);
    }
}
