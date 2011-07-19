package org.soc.common.game;

import org.soc.common.game.phases.GamePhase;

import com.google.gwt.event.shared.GwtEvent;

public class GamePhaseChangedEvent extends GwtEvent<GamePhaseChangedEventHandler>
{
    public static Type<GamePhaseChangedEventHandler> TYPE = new Type<GamePhaseChangedEventHandler>();
    private GamePhase previousPhase;
    private GamePhase newPhase;
    
    
    public GamePhaseChangedEvent(GamePhase previousPhase, GamePhase newPhase)
    {
        super();
        this.previousPhase = previousPhase;
        this.newPhase = newPhase;
    }

    /**
     * @return the previousPhase
     */
    public GamePhase getPreviousPhase()
    {
        return previousPhase;
    }

    /**
     * @return the newPhase
     */
    public GamePhase getNewPhase()
    {
        return newPhase;
    }

    @Override
    protected void dispatch(GamePhaseChangedEventHandler handler)
    {
        handler.onGamePhaseChanged(this);
    }

    @Override
    public Type<GamePhaseChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
