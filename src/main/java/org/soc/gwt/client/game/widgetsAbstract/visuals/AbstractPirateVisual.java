package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.game.pieces.Pirate;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PirateVisual;

public class AbstractPirateVisual extends AbstractPieceVisual implements
        PirateVisual
{
  private Pirate pirate;

  public AbstractPirateVisual(Pirate pirate)
  {
    super();
    this.pirate = pirate;
  }
  @Override public Pirate getPirate()
  {
    return pirate;
  }
  /* (non-Javadoc)
   * 
   * @seesoc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual# getPirateVisual() */
  @Override public PirateVisual getPirateVisual()
  {
    return this;
  }
}
