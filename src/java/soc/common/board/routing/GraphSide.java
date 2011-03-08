package soc.common.board.routing;

import soc.common.board.layout.HasSide;
import soc.common.board.layout.HexSide;

public interface GraphSide extends GraphElement
{
    public HexSide getSide();

    public boolean isBuildable();

    public HasSide getSidePiece();
}
