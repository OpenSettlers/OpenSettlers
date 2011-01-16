package soc.common.game.logs;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.event.shared.GwtEvent;

public class ActionQueueChangedEvent extends
        GwtEvent<ActionQueueChangedEventHandler>
{
    public static Type<ActionQueueChangedEventHandler> TYPE = new Type<ActionQueueChangedEventHandler>();
    private GameAction dequeuedAction;
    private GameAction enqueuedAction;

    public ActionQueueChangedEvent(GameAction dequeuedAction,
            GameAction enqueuedAction)
    {
        super();
        this.dequeuedAction = dequeuedAction;
        this.enqueuedAction = enqueuedAction;
    }

    /**
     * @return the dequeuedAction
     */
    public GameAction getDequeuedAction()
    {
        return dequeuedAction;
    }

    /**
     * @return the enqueuedAction
     */
    public GameAction getEnqueuedAction()
    {
        return enqueuedAction;
    }

    public GameAction getChangedAction()
    {
        return enqueuedAction == null ? dequeuedAction : enqueuedAction;
    }

    @Override
    protected void dispatch(ActionQueueChangedEventHandler handler)
    {
        handler.onQueueChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ActionQueueChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
