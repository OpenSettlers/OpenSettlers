package soc.common.board.hexes;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.board.territories.Territory;

public abstract class LandHex extends Hex implements ITerritoryHex
{
    //ID of territory hex belongs to. Default on mainland (ID=0).
    protected Territory territory;
    protected SimpleEventBus eventBus = new SimpleEventBus(); 

    public HandlerRegistration addTerritoryChangedEventHandler(TerritoryChangedEventHandler handler)
    {
        return eventBus.addHandler(TerritoryChangedEvent.TYPE, handler);
    }
    
    @Override
    public Territory getTerritory()
    {
        return territory;
    }

    @Override
    public ITerritoryHex setTerritory(Territory t)
    {
        if (t != territory)
        {
            this.territory=t;
            eventBus.fireEvent(new TerritoryChangedEvent(t));
        }
        
        return (ITerritoryHex)this;
    }

}
