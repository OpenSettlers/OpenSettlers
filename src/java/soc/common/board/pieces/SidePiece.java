package soc.common.board.pieces;

import soc.common.board.HexSide;

public interface SidePiece
{
    public HexSide getSide();

    public SidePiece setSide(HexSide side);
}
