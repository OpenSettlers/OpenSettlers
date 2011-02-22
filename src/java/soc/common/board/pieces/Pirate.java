package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.HexLocation;
import soc.common.board.pieces.abstractPieces.AbstractPiece;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;

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
        return this;
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createPirateVisual(this);
    }
}
