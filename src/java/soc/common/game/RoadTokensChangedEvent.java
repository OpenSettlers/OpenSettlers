package soc.common.game;

import com.google.gwt.event.shared.GwtEvent;

public class RoadTokensChangedEvent extends
        GwtEvent<RoadTokensChangedEventHandler>
{
    public static Type<RoadTokensChangedEventHandler> TYPE = new Type<RoadTokensChangedEventHandler>();
    private int newTokenAmount =0;
    
    public RoadTokensChangedEvent(int newTokenAmount)
    {
        super();
        this.newTokenAmount = newTokenAmount;
    }

    /**
     * @return the newTokenAmount
     */
    public int getNewTokenAmount()
    {
        return newTokenAmount;
    }

    @Override
    protected void dispatch(RoadTokensChangedEventHandler arg0)
    {
        arg0.onRoadTokensChanged(this); 
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RoadTokensChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
