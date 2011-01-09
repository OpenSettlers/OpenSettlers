package soc.common.board.pieces;

import soc.common.annotations.CitiesKnights;
import soc.common.annotations.Pioneers;
import soc.common.board.HexPoint;

@CitiesKnights
@Pioneers
public class Wall extends AbstractPlayerPiece implements PointPiece
{
    private static final long serialVersionUID = 5417867350330920841L;
    private HexPoint pointLocation;

    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    @Override
    public PointPiece setPoint(HexPoint point)
    {
        this.pointLocation = point;
        return this;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

}
