package soc.common.board.layout;

import soc.common.board.pieces.abstractPieces.BoardPiece;
import soc.common.board.routing.GraphPoint;

public interface HasSide extends BoardPiece
{
    public HexSide getSide();

    public HasSide setSide(HexSide side);

    public boolean canConnect(GraphPoint graphPoint, HasSide otherPiece);

    public boolean connectsWithRoad();

    public boolean connectsWithShip();

    public boolean connectsWithBridge();
}
