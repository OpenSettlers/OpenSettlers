package soc.gwtClient.editor;

import soc.gwtClient.visuals.behaviour.InteractionBehaviour;

import com.google.gwt.event.shared.GwtEvent;


public class BehaviourChanged extends GwtEvent<BehaviourChangedHandler>
{
    public static Type<BehaviourChangedHandler> TYPE = new Type<BehaviourChangedHandler>();
    InteractionBehaviour behaviour;
    
    public BehaviourChanged(InteractionBehaviour behaviour)
    {
        this.behaviour=behaviour;
    }
    /**
     * @return the behaviour
     */
    public InteractionBehaviour getBehaviour()
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
