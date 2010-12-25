package soc.common.board.pieces;

import soc.common.board.HexPoint;

public interface IPointPiece
{
    public HexPoint getPoint();
    public IPointPiece setPoint(HexPoint point);
}
