package org.soc.common.game;

import com.google.gwt.event.shared.GwtEvent;

public class TurnChangedEvent extends GwtEvent<TurnChangedEventHandler>
{
    public static Type<TurnChangedEventHandler> TYPE = new Type<TurnChangedEventHandler>();
    private Turn oldTurn;
    private Turn newTurn;

    public TurnChangedEvent(Turn oldTurn, Turn newTurn)
    {
        super();
        this.oldTurn = oldTurn;
        this.newTurn = newTurn;
    }

    /**
     * @return the oldTurn
     */
    public Turn getOldTurn()
    {
        return oldTurn;
    }

    /**
     * @return the newTurn
     */
    public Turn getNewTurn()
    {
        return newTurn;
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
