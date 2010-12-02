package soc.common.board.hexes;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import soc.common.annotations.SeaFarers;
import soc.common.board.Territory;

@SuppressWarnings("deprecation")
@SeaFarers
public class DiscoveryHex extends Hex implements ITerritoryHex, HasHandlers
{
    protected Territory territory;
    private HandlerManager handlerManager = new HandlerManager(this);
    
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
        return handlerManager.addHandler(TerritoryChangedEvent.TYPE, handler);
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
        
        fireEvent(new TerritoryChangedEvent(territory));
        
        return this;
    }

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }

}
