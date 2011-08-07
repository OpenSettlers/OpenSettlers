package org.soc.common.board.routing;

import org.soc.common.board.layout.HasSide;
import org.soc.common.board.layout.HexSide;

public interface GraphSide extends GraphElement
{
    public HexSide getSide();

    public boolean isBuildable();

    public HasSide getSidePiece();
}
