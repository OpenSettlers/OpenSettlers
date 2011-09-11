package org.soc.gwt.client.editor;

import org.soc.common.game.actions.ActionOnBoard;

import com.google.gwt.event.shared.GwtEvent;


public class BehaviourChanged extends GwtEvent<BehaviourChangedHandler>
{
    public static Type<BehaviourChangedHandler> TYPE = new Type<BehaviourChangedHandler>();
    ActionOnBoard behaviour;
    
    public BehaviourChanged(ActionOnBoard behaviour)
    {
        this.behaviour=behaviour;
    }
    /**
     * @return the behaviour
     */
    public ActionOnBoard getBehaviour()
    {
        return behaviour;
    }

    @Override
    protected void dispatch(BehaviourChangedHandler handler)
    {
        handler.onBehaviourChanged(this);
        
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<BehaviourChangedHandler> getAssociatedType()
    {
        return TYPE;
    }

}
