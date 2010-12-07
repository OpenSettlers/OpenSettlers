package soc.common.board.hexes;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.annotations.SeaFarers;
import soc.common.board.Territory;

@SeaFarers
public class DiscoveryHex extends Hex implements ITerritoryHex
{
    protected Territory territory;
    protected SimpleEventBus eventBus = new SimpleEventBus(); 
    
    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        DiscoveryHex result = new DiscoveryHex();
        
        result.setTerritory(territory);
        
        return result;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "White";
    }

    @Override
    public HandlerRegistration addTerritoryChangedEventHandler(
            TerritoryChangedEventHandler handler)
    {
        return eventBus.addHandler(TerritoryChangedEvent.TYPE, handler);
    }

    @Override
    public Territory getTerritory()
    {
        return territory;
    }

    @Override
    public ITerritoryHex setTerritory(Territory territory)
    {
        this.territory=territory;
        
        eventBus.fireEvent(new TerritoryChangedEvent(territory));
        
        return this;
    }
}
