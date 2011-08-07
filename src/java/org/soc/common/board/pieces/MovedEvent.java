package org.soc.common.board.pieces;

import org.soc.common.board.layout.HexLocation;

import com.google.gwt.event.shared.GwtEvent;

public class MovedEvent extends GwtEvent<MovedEventHandler>
{
    public static Type<MovedEventHandler> TYPE = new Type<MovedEventHandler>();
    private HexLocation newLocation;
    private HexLocation oldLocation;

    public MovedEvent(HexLocation newLocation, HexLocation oldLocation)
    {
        super();
        this.newLocation = newLocation;
        this.oldLocation = oldLocation;
    }

    /**
     * @return the newLocation
     */
    public HexLocation getNewLocation()
    {
        return newLocation;
    }

    /**
     * @return the oldLocation
     */
    public HexLocation getOldLocation()
    {
        return oldLocation;
    }

    @Override
    protected void dispatch(MovedEventHandler handler)
    {
        handler.onMoved(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<MovedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
