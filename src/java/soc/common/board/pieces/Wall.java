package soc.common.board.pieces;

import soc.common.annotations.CitiesKnights;
import soc.common.annotations.Pioneers;
import soc.common.board.HexPoint;

@CitiesKnights
@Pioneers
public class Wall extends AbstractPlayerPiece implements PointPiece
{
    private static final long serialVersionUID = 5417867350330920841L;

    @Override
    public HexPoint getPoint()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PointPiece setPoint(HexPoint point)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

}
