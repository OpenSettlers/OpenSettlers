package soc.common.board.routing;

import soc.common.board.HexSide;
import soc.common.board.pieces.abstractPieces.SidePiece;

public interface GraphSide extends GraphElement
{
    public HexSide getSide();

    public boolean isBuildable();

    public SidePiece getSidePiece();
}
