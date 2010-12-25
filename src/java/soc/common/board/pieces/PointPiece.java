package soc.common.board.pieces;

import soc.common.board.HexPoint;

public interface PointPiece
{
    public HexPoint getPoint();
    public PointPiece setPoint(HexPoint point);
}
