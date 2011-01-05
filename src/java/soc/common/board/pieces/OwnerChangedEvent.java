package soc.common.board.pieces;

import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.GwtEvent;

public class OwnerChangedEvent extends GwtEvent<OwnerChangedEventHandler>
{
    public static Type<OwnerChangedEventHandler> TYPE = new Type<OwnerChangedEventHandler>();
    private GamePlayer oldOwner;
    private GamePlayer newOwner;

    public OwnerChangedEvent(GamePlayer oldOwner, GamePlayer newOwner)
    {
        super();
        this.oldOwner = oldOwner;
        this.newOwner = newOwner;
    }

    /**
     * @return the oldOwner
     */
    public GamePlayer getOldOwner()
    {
        return oldOwner;
    }

    /**
     * @return the newOwner
     */
    public GamePlayer getNewOwner()
    {
        return newOwner;
    }

    @Override
    protected void dispatch(OwnerChangedEventHandler handler)
    {
        handler.onOwnerChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<OwnerChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
