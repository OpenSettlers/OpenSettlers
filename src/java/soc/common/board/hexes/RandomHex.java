package soc.common.board.hexes;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.board.territories.Territory;

public class RandomHex extends Hex implements ITerritoryHex
{
    private Territory territory;
    protected SimpleEventBus eventBus = new SimpleEventBus(); 

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        return new RandomHex();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "White";
    }

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
