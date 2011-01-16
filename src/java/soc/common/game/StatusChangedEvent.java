package soc.common.game;

import soc.common.game.statuses.GameStatus;

import com.google.gwt.event.shared.GwtEvent;

public class StatusChangedEvent extends GwtEvent<StatusChangedEventHandler>
{
    public static Type<StatusChangedEventHandler> TYPE = new Type<StatusChangedEventHandler>();
    private GameStatus newStatus;
    private GameStatus oldStatus;

    public StatusChangedEvent(GameStatus newStatus, GameStatus oldStatus)
    {
        super();
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    /**
     * @return the newStatus
     */
    public GameStatus getNewStatus()
    {
        return newStatus;
    }

    /**
     * @return the oldStatus
     */
    public GameStatus getOldStatus()
    {
        return oldStatus;
    }

    @Override
    protected void dispatch(StatusChangedEventHandler handler)
    {
        handler.onStatusChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<StatusChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
