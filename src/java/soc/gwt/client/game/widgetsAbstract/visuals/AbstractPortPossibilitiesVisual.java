package soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.layout.HexLocation;
import soc.common.board.layout.RotationPosition;
import soc.common.board.ports.AbstractPort;
import soc.common.board.ports.PossiblePort;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.PortPossibilitiesVisual;
import soc.common.views.widgetsInterface.visuals.PortVisual;

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

    @Override
    public void updatePossibilities()
    {
        // Test if the other hex of the hexside is a landhex, add it to the
        // allowed
        // hexes when so.
        for (PortVisual possibility : portVisuals)
        {
            HexLocation landLocation = possibility.getPort().getLandLocation();
            // Update validity
            possibility.setValid(parent.getBoard().getHexes().isValid(
                    landLocation));
        }
    }
}
