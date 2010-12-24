package soc.common.board.routing;

import soc.common.board.HexSide;

public interface IGraphSide extends IGraphElement
{
    public HexSide getSide();
    public boolean isBuildable();
    public IGraphPoint setBuildable(boolean townBuildable);
}
