package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.pieces.Road;
import soc.common.views.widgetsInterface.visuals.RoadVisual;

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#getRoadVisual
     * ()
     */
    @Override
    public RoadVisual getRoadVisual()
    {
        return this;
    }

}
