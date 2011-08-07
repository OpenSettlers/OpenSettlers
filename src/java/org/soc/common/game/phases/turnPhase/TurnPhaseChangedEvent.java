package org.soc.common.game.phases.turnPhase;

import com.google.gwt.event.shared.GwtEvent;

public class TurnPhaseChangedEvent extends GwtEvent<TurnPhaseChangedHandler>
{
    public static Type<TurnPhaseChangedHandler> TYPE = new Type<TurnPhaseChangedHandler>();
    private TurnPhase newPhase;
    private TurnPhase oldPhase;

    public TurnPhaseChangedEvent(TurnPhase newPhase, TurnPhase oldPhase)
    {
        super();
        this.newPhase = newPhase;
        this.oldPhase = oldPhase;
    }

    /**
     * @return the newPhase
     */
    public TurnPhase getNewPhase()
    {
        return newPhase;
    }

    /**
     * @return the oldPhase
     */
    public TurnPhase getOldPhase()
    {
        return oldPhase;
    }

    @Override
    protected void dispatch(TurnPhaseChangedHandler handler)
    {
        handler.onTurnPhaseChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<TurnPhaseChangedHandler> getAssociatedType()
    {
        return TYPE;
    }

}
