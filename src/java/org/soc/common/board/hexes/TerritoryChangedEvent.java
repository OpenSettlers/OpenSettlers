package org.soc.common.board.hexes;

import org.soc.common.board.territories.Territory;

import com.google.gwt.event.shared.GwtEvent;

public class TerritoryChangedEvent extends GwtEvent<TerritoryChangedEventHandler>
{
    public static Type<TerritoryChangedEventHandler> TYPE = new Type<TerritoryChangedEventHandler>();
    private Territory territory;
    
    public TerritoryChangedEvent(Territory territory)
    {
        this.territory=territory;
    }
    
    /**
     * @return the addedTerritory
     */
    public Territory getTerritory()
    {
        return territory;
    }


    @Override
    protected void dispatch(TerritoryChangedEventHandler handler)
    {
        handler.onTerritoryChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<TerritoryChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }
}
