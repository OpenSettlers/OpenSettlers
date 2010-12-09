package soc.common.game;

import com.google.gwt.event.shared.GwtEvent;

public class VictoryPointsChangedEvent extends
        GwtEvent<VictoryPointsChangedEventHandler>
{
    public static Type<VictoryPointsChangedEventHandler> TYPE = new Type<VictoryPointsChangedEventHandler>();
    private IVictoryPointItem addedPoint;
    private IVictoryPointItem removedPoint;
    
    public VictoryPointsChangedEvent(IVictoryPointItem addedPoint,
            IVictoryPointItem removedPoint)
    {
        super();
        this.addedPoint = addedPoint;
        this.removedPoint = removedPoint;
    }

    /**
     * @return the addedPoint
     */
    public IVictoryPointItem getAddedPoint()
    {
        return addedPoint;
    }

    /**
     * @return the removedPoint
     */
    public IVictoryPointItem getRemovedPoint()
    {
        return removedPoint;
    }

    @Override
    protected void dispatch(VictoryPointsChangedEventHandler handler)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<VictoryPointsChangedEventHandler> getAssociatedType()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
