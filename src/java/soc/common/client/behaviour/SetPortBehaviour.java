package soc.common.client.behaviour;

import soc.common.board.hexes.SeaHex;
import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.IHexVisual;

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
    public void clicked(IPieceVisual hex, IBoardVisual board)
    {
        if (hex instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)hex;
            if (hexVisual.getHex() instanceof SeaHex)
            {

            }
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
            }
        }
    }

    @Override
    public void mouseOut(IPieceVisual hex, IBoardVisual board)
    {
        hex.setSelected(false);
    }

}
