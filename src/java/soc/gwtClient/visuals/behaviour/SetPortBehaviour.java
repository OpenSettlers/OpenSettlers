package soc.gwtClient.visuals.behaviour;

import soc.common.board.hexes.SeaHex;
import soc.common.board.ports.Port;
import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.HexVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PortVisual;

public class SetPortBehaviour implements BoardBehaviour
{
    private Port port;
    private HexVisual currentHexVisual;

    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public SetPortBehaviour setPort(Port p)
    {
        this.port = p;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof PortVisual)
        {
            PortVisual portVisual = (PortVisual) pieceVisual;
            SeaHex seaHex = (SeaHex) currentHexVisual.getHex();

            Port p = port.copy();
            p.setRotationPosition(portVisual.getPort().getRotationPosition());
            p.setHexLocation(seaHex.getLocation());
            seaHex.setPort(p);
        }
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            if (hexVisual.getHex() instanceof SeaHex)
            {
                hexVisual.setSelected(true);
                hexVisual.getPortPossibilitiesVisual().setVisible(true);
                hexVisual.getPortPossibilitiesVisual().updatePossibilities();
                currentHexVisual = hexVisual;
            }
        }
        if (pieceVisual instanceof PortVisual)
        {
            PortVisual portVisual = (PortVisual) pieceVisual;
            portVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            if (hexVisual.getHex() instanceof SeaHex)
            {
                hexVisual.setSelected(false);
                hexVisual.getPortPossibilitiesVisual().setVisible(false);
                currentHexVisual = null;
            }
        }
        if (pieceVisual instanceof PortVisual)
        {
            PortVisual portVisual = (PortVisual) pieceVisual;
            portVisual.setSelected(false);
        }
    }

}
