package soc.common.board.routing;

import soc.common.board.HexPoint;

public interface IGraphPoint extends IGraphElement 
{
    public HexPoint getPoint();
    
    /*
     * Returns true when a town is allowed to build on it
     */
    public boolean isTownBuildable();
    public IGraphPoint setTownBuildable(boolean townBuildable);
}
