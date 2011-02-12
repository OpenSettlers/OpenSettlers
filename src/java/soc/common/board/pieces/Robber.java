package soc.common.board.pieces;

import soc.common.board.HexLocation;
import soc.common.board.pieces.abstractPieces.AbstractPiece;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class Robber extends AbstractPiece
{
    private static final long serialVersionUID = 2162591486291994070L;
    private HexLocation location;
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public Robber(HexLocation hexLocation)
    {
        this.location = hexLocation;
    }

    /**
     * @return the location
     */
    public HexLocation getLocation()
    {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public Robber setLocation(HexLocation location)
    {
        if (!this.location.equals(location))
        {
            HexLocation oldLocation = this.location;
            this.location = location;
            eventBus.fireEvent(new MovedEvent(location, oldLocation));
        }

        return this;
    }

    public HandlerRegistration addMoveEventHandler(MovedEventHandler handler)
    {
        return eventBus.addHandler(MovedEvent.TYPE, handler);
    }
}
