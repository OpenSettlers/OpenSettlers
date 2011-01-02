package soc.common.client.visuals.game;

import soc.common.board.pieces.Road;
import soc.common.client.visuals.AbstractPieceVisual;

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
