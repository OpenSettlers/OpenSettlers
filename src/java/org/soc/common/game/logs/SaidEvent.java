package org.soc.common.game.logs;

import org.soc.common.actions.gameAction.meta.GameChat;

import com.google.gwt.event.shared.GwtEvent;

/*
 * Occurs when a player said something during a game using a text chat interface
 */
public class SaidEvent extends GwtEvent<SaidEventHandler>
{
    public static Type<SaidEventHandler> TYPE = new Type<SaidEventHandler>();
    private GameChat said;
    
    public SaidEvent(GameChat said)
    {
        super();
        this.said = said;
    }

    /**
     * @return the said
     */
    public GameChat getSaid()
    {
        return said;
    }

    @Override
    protected void dispatch(SaidEventHandler handler)
    {
        handler.onSaid(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<SaidEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
