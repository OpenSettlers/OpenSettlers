package soc.common.board.hexes;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

import soc.common.board.Territory;

@SuppressWarnings("deprecation")
public abstract class LandHex extends Hex implements ITerritoryHex
{
    //ID of territory hex belongs to. Default on mainland (ID=0).
    protected Territory territory;
    private HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }
    
    @Override
    public HandlerRegistration addTerritoryChangedEventHandler(TerritoryChangedEventHandler handler)
    {
        return handlerManager.addHandler(TerritoryChangedEvent.TYPE, handler);
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
            fireEvent(new TerritoryChangedEvent(t));
        }
        
        return (ITerritoryHex)this;
    }

}
