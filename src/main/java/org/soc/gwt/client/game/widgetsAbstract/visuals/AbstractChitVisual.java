package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.game.Chit;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.ChitVisual;

public abstract class AbstractChitVisual extends AbstractPieceVisual implements
        ChitVisual
{
  protected Chit chit;
  final protected BoardVisual parent;

  protected void updateChit()
  {}
  public AbstractChitVisual(Chit chit, BoardVisual parent)
  {
    this.chit = chit;
    this.parent = parent;
  }
  @Override public Chit getChit()
  {
    return chit;
  }
  @Override public ChitVisual setChit(Chit chit)
  {
    this.chit = chit;
    updateChit();
    return this;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getChitVisual () */
  @Override public ChitVisual getChitVisual()
  {
    return this;
  }
}
