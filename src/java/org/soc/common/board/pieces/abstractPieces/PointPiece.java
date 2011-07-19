package org.soc.common.board.pieces.abstractPieces;

import org.soc.common.board.layout.HexPoint;

public interface PointPiece extends BoardPiece
{
    public HexPoint getPoint();

    public PointPiece setPoint(HexPoint point);
}
