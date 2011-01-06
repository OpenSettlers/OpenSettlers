package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.pieces.Road;

public abstract class AbstractRoadVisual extends AbstractPieceVisual implements
        RoadVisual
{
    protected Road road;

    public AbstractRoadVisual(Road road)
    {
        super();
        this.road = road;
    }

    /**
     * @return the road
     */
    public Road getRoad()
    {
        return road;
    }

}
