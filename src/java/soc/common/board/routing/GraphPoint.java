package soc.common.board.routing;

import soc.common.board.HexPoint;

public interface GraphPoint extends GraphElement 
{
    public HexPoint getPoint();
    
    /*
     * Returns true when a town is allowed to build on it
     */
    public boolean isTownBuildable();
    public GraphPoint setTownBuildable(boolean townBuildable);
}
