package soc.common.game;

import com.google.gwt.event.shared.GwtEvent;

public class TurnChangedEvent extends GwtEvent<TurnChangedEventHandler>
{
    public static Type<TurnChangedEventHandler> TYPE = new Type<TurnChangedEventHandler>();
    boolean isOnTurn;
    
    /**
     * @return the isOnTurn
     */
    public boolean isOnTurn()
    {
        return isOnTurn;
    }

    public TurnChangedEvent(boolean isOnTurn)
    {
        super();
        this.isOnTurn = isOnTurn;
    }

    @Override
    protected void dispatch(TurnChangedEventHandler arg0)
    {
        arg0.onTurnChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<TurnChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
