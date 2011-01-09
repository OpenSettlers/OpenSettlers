package soc.common.board.territories;

import com.google.gwt.event.shared.GwtEvent;

public class TerritoriesChangedEvent extends
        GwtEvent<TerritoriesChangedEventHandler>
{
    public static Type<TerritoriesChangedEventHandler> TYPE = new Type<TerritoriesChangedEventHandler>();
    private Territory addedTerritory;
    private Territory removedTerritory;

    public TerritoriesChangedEvent(Territory addedTerritory,
            Territory removedTerritory)
    {
        super();
        this.addedTerritory = addedTerritory;
        this.removedTerritory = removedTerritory;
    }

    /**
     * @return the removedTerritory
     */
    public Territory getRemovedTerritory()
    {
        return removedTerritory;
    }

    /**
     * @return the addedTerritory
     */
    public Territory getAddedTerritory()
    {
        return addedTerritory;
    }

    @Override
    protected void dispatch(TerritoriesChangedEventHandler handler)
    {
        handler.onTerritoriesChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<TerritoriesChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
