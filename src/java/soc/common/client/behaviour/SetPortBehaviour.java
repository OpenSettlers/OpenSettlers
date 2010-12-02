package soc.common.client.behaviour;

import soc.common.board.hexes.SeaHex;
import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.IHexVisual;
import soc.common.client.visuals.board.IPortVisual;

public class SetPortBehaviour implements IInteractionBehaviour
{
    private Port port;
    private IHexVisual currentHexVisual;
    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public SetPortBehaviour setPort(Port p)
    {
        this.port = p;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IPortVisual)
        {
            IPortVisual portVisual = (IPortVisual)pieceVisual;
            SeaHex seaHex = (SeaHex)currentHexVisual.getHex();
            Port p = new Port(seaHex.getLocation(), portVisual.getPort().getRotationPosition());
            seaHex.setPort(p);
        }
    }

    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)pieceVisual; 
            if (hexVisual.getHex() instanceof SeaHex)
            {
                hexVisual.setSelected(true);
                hexVisual.getPortPossibilitiesVisual().setVisible(true);
                hexVisual.getPortPossibilitiesVisual().updatePossibilities();
                currentHexVisual = hexVisual;
            }
        }        
        if (pieceVisual instanceof IPortVisual)
        {
            IPortVisual portVisual = (IPortVisual)pieceVisual; 
            portVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)pieceVisual; 
            if (hexVisual.getHex() instanceof SeaHex)
            {
                hexVisual.setSelected(false);
                hexVisual.getPortPossibilitiesVisual().setVisible(false);
                currentHexVisual=null;
            }
        }
        if (pieceVisual instanceof IPortVisual)
        {
            IPortVisual portVisual = (IPortVisual)pieceVisual; 
            portVisual.setSelected(false);
        }
    }

}
