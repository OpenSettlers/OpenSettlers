package soc.common.client.visuals.board;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;
import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.PieceVisual;

public abstract class PortPossibilitiesVisual extends PieceVisual implements IPortPossibilitiesVisual
{
    protected List<Port> ports = new ArrayList<Port>();
    protected List<IPortVisual> portVisuals = new ArrayList<IPortVisual>();
    protected HexLocation hexLocation;
    protected IBoardVisual parent;

    public PortPossibilitiesVisual(HexLocation hexLocation, IBoardVisual parent)
    {
        this.parent=parent;
        this.hexLocation=hexLocation;
        
        ports.add(new Port(hexLocation, RotationPosition.DEG0));
        ports.add(new Port(hexLocation, RotationPosition.DEG60));
        ports.add(new Port(hexLocation, RotationPosition.DEG120));
        ports.add(new Port(hexLocation, RotationPosition.DEG180));
        ports.add(new Port(hexLocation, RotationPosition.DEG240));
        ports.add(new Port(hexLocation, RotationPosition.DEG300));
    }
    
    @Override
    public void updatePossibilities()
    {
        // Test if the other hex of the hexside is a landhex, add it to the allowed
        // hexes when so.
        for (IPortVisual possibility : portVisuals)
        {
            HexLocation landLocation = possibility.getPort().getLandLocation();
            // Update validity
            possibility.setValid(parent.getBoard().getHexes().isValid(landLocation));
        }
    }
}
