package soc.common.board.layout;

import soc.common.board.pieces.abstractPieces.BoardPiece;

/*
 * For pieces on the board located at a HexPoint
 */
public interface HasPoint extends BoardPiece
{
    public HexPoint getPoint();

    public HasPoint setPoint(HexPoint point);
}
