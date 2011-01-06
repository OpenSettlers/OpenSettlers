package soc.common.board.routing;

import soc.common.board.HexSide;

public interface GraphSide extends GraphElement
{
    public HexSide getSide();

    public boolean isBuildable();
}
