package soc.common.board.pieces;

import soc.common.board.HexPoint;

/*
 * PlayerPiece which is built on a HexPoint. Examples:
 * Town, City, Knight
 */
public interface IPointPiece
{
    public HexPoint getPoint();
    public IPointPiece setPoint(HexPoint point);
}
