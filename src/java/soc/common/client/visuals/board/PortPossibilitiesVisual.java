package soc.common.client.visuals.board;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;
import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;

public abstract class PortPossibilitiesVisual implements IPortPossibilitiesVisual
{
    protected List<Port> ports = new ArrayList<Port>();
    protected List<IPortVisual> portVisuals = new ArrayList<IPortVisual>();
    protected HexLocation hexLocation;
    protected IBoardVisual parent;
    
    protected boolean visible = true;
    protected boolean selected = false;
    protected boolean enabled = true;
    protected void updateVisible() {}
    
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
            // Update validity
            possibility.setValid(parent.getBoard().getHexes().isValid(hexLocation));
        }
    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSelected()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isVisible()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IPieceVisual setEnabled(boolean enabled)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPieceVisual setSelected(boolean selected)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPieceVisual setVisible(boolean visible)
    {
        this.visible=visible;
        
        updateVisible();

        return this;
    }


}
