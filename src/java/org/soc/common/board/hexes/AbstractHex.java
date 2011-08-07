package org.soc.common.board.hexes;

import org.soc.common.board.layout.HexLocation;
import org.soc.common.board.ports.Port;
import org.soc.common.board.ports.PortChangedEvent;
import org.soc.common.board.ports.PortChangedEventHandler;
import org.soc.common.board.resources.Resource;
import org.soc.common.board.territories.Territory;
import org.soc.common.utils.ClassUtils;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/* Represents the base type for each hex.
 * @seealso cref="http://www.codeproject.com/KB/cs/hexagonal_part1.aspx"/>
 * @seealso cref="http://gmc.yoyogames.com/index.php?showtopic=336183"/>
 */
public abstract class AbstractHex implements Hex
{
    private static final long serialVersionUID = -1737108038985903164L;
    protected HexLocation hexLocation;
    protected Territory territory;
    protected SimpleEventBus eventBus = new SimpleEventBus();
    protected String name;

    @Override
    public Territory getTerritory()
    {
        return territory;
    }

    @Override
    public Hex setTerritory(Territory t)
    {
        if (t != territory)
        {
            this.territory = t;
            eventBus.fireEvent(new TerritoryChangedEvent(t));
        }

        return this;
    }

    public AbstractHex()
    {
        name = ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    public Hex setLocation(HexLocation hexLocation)
    {
        this.hexLocation = hexLocation;

        return this;
    }

    public HexLocation getLocation()
    {
        return hexLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return this.getClass().toString() + " [hexLocation=" + hexLocation
                + "]";
    }

    public String getName()
    {
        return name;
    }

    public HandlerRegistration addTerritoryChangedEventHandler(
            TerritoryChangedEventHandler handler)
    {
        return eventBus.addHandler(TerritoryChangedEvent.TYPE, handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#getPort()
     */
    @Override
    public Port getPort()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#hasPort()
     */
    @Override
    public boolean canHavePort()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#setPort(org.soc.common.board.ports.Port)
     */
    @Override
    public Hex setPort(Port port)
    {
        return this;
    }

    public HandlerRegistration addChitChangedEventHandler(
            ChitChangedEventHandler handler)
    {
        return eventBus.addHandler(ChitChangedEvent.TYPE, handler);
    }

    public HandlerRegistration addPortChangedEventHandler(
            PortChangedEventHandler handler)
    {
        return eventBus.addHandler(PortChangedEvent.TYPE, handler);
    }
}