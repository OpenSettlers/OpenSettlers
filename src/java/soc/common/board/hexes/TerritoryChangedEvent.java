package soc.common.board.hexes;

import soc.common.board.Territory;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;


public class TerritoryChangedEvent extends GwtEvent<ChitChangedEventHandler>
{
    public static Type<TerritoryChangedEventHandler> TYPE = new Type<TerritoryChangedEventHandler>();
    public Territory territory;
    
    public TerritoryChangedEvent(Territory territory)
    {
        this.territory=territory;
    }
    /**
     * @return the territory
     */
    public Territory getTerritory()
    {
        return territory;
    }
    @Override
    protected void dispatch(ChitChangedEventHandler handler)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ChitChangedEventHandler> getAssociatedType()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
