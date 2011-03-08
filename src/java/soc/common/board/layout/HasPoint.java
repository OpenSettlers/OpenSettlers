package soc.common.board.layout;

import soc.common.board.pieces.abstractPieces.BoardPiece;

public interface HasPoint extends BoardPiece
{
    public HexPoint getPoint();

    public HasPoint setPoint(HexPoint point);
}
