package soc.common.board.routing;

import java.io.Serializable;

import soc.common.board.layout.HasPoint;
import soc.common.board.layout.HexPoint;

public interface GraphPoint extends GraphElement, Serializable
{
    public HexPoint getPoint();

    /*
     * Returns true when a town is allowed to build on it
     */
    public boolean isTownBuildable();

    public GraphPoint setTownBuildable(boolean townBuildable);

    public HasPoint getPointPiece();
}
