package soc.common.game.logs;

import com.google.gwt.event.shared.GwtEvent;

public class ActionQueueChangedEvent extends
        GwtEvent<ActionQueueChangedEventHandler>
{
    public static Type<ActionQueueChangedEventHandler> TYPE = new Type<ActionQueueChangedEventHandler>();
    private QueuedAction dequeuedAction;
    private QueuedAction enqueuedAction;
    
    public ActionQueueChangedEvent(QueuedAction dequeuedAction,
            QueuedAction enqueuedAction)
    {
        super();
        this.dequeuedAction = dequeuedAction;
        this.enqueuedAction = enqueuedAction;
    }

    /**
     * @return the dequeuedAction
     */
    public QueuedAction getDequeuedAction()
    {
        return dequeuedAction;
    }

    /**
     * @return the enqueuedAction
     */
    public QueuedAction getEnqueuedAction()
    {
        return enqueuedAction;
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
