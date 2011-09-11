package org.soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.board.HexSide;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.SideVisual;

public abstract class AbstractSideVisual extends AbstractPieceVisual implements
        SideVisual
{
  private HexSide hexSide;
  protected List<PieceVisual> pieceVisuals = new ArrayList<PieceVisual>();

  public AbstractSideVisual(HexSide hexSide)
  {
    super();
    this.hexSide = hexSide;
  }
  @Override public HexSide getHexSide()
  {
    return hexSide;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getSideVisual () */
  @Override public SideVisual sideVisual()
  {
    return this;
  }
}
