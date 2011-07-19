package soc.gwt.client.editor;

import soc.common.views.behaviour.board.BoardBehaviour;

import com.google.gwt.event.shared.GwtEvent;


public class BehaviourChanged extends GwtEvent<BehaviourChangedHandler>
{
    public static Type<BehaviourChangedHandler> TYPE = new Type<BehaviourChangedHandler>();
    BoardBehaviour behaviour;
    
    public BehaviourChanged(BoardBehaviour behaviour)
    {
        this.behaviour=behaviour;
    }
    /**
     * @return the behaviour
     */
    public BoardBehaviour getBehaviour()
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