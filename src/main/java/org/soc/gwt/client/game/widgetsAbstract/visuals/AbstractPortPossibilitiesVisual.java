package org.soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.Port.AbstractPort;
import org.soc.common.game.Port.PossiblePort;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.RotationPosition;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PortPossibilitiesVisual;

public abstract class AbstractPortPossibilitiesVisual extends AbstractPieceVisual implements
        PortPossibilitiesVisual
{
  protected List<AbstractPort> ports = new ArrayList<AbstractPort>();
  protected List<PortVisual> portVisuals = new ArrayList<PortVisual>();
  protected HexLocation hexLocation;
  protected BoardVisual parent;

  public AbstractPortPossibilitiesVisual(HexLocation hexLocation, BoardVisual parent)
  {
    this.parent = parent;
    this.hexLocation = hexLocation;
    ports.add(new PossiblePort(hexLocation, RotationPosition.DEG0));
    ports.add(new PossiblePort(hexLocation, RotationPosition.DEG60));
    ports.add(new PossiblePort(hexLocation, RotationPosition.DEG120));
    ports.add(new PossiblePort(hexLocation, RotationPosition.DEG180));
    ports.add(new PossiblePort(hexLocation, RotationPosition.DEG240));
    ports.add(new PossiblePort(hexLocation, RotationPosition.DEG300));
  }
  @Override public void updatePossibilities()
  {
    // Test if the other hex of the hexside is a landhex, add it to the
    // allowed
    // hexes when so.
    for (PortVisual possibility : portVisuals)
    {
      HexLocation landLocation = possibility.getPort().landLocation();
      // Update validity
      possibility.setValid(parent.board().hexes().isValid(
              landLocation));
    }
  }
}
