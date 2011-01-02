package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.HexLocation;

@SeaFarers
public class Pirate extends AbstractPiece
{
    private static final long serialVersionUID = -2575172800909661845L;
    private HexLocation location;

    public Pirate(HexLocation hexLocation)
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
    public Pirate setLocation(HexLocation location)
    {
        this.location = location;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
}
