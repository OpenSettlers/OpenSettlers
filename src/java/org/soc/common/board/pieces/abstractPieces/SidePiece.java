package org.soc.common.board.pieces.abstractPieces;

import org.soc.common.board.layout.HexSide;
import org.soc.common.board.routing.GraphPoint;

public interface SidePiece extends BoardPiece
{
    public HexSide getSide();

    public SidePiece setSide(HexSide side);

    public boolean canConnect(GraphPoint graphPoint, SidePiece otherPiece);

    public boolean connectsWithRoad();

    public boolean connectsWithShip();

    public boolean connectsWithBridge();
}
