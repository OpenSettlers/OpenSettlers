package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.game.pieces.Road;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.RoadVisual;

public abstract class AbstractRoadVisual extends AbstractPieceVisual implements
        RoadVisual
{
  protected Road road;

  public AbstractRoadVisual(Road road)
  {
    super();
    this.road = road;
  }
  /** @return the road */
  public Road getRoad()
  {
    return road;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getRoadVisual () */
  @Override public RoadVisual getRoadVisual()
  {
    return this;
  }
}
