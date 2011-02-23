package soc.gwtClient.game.behaviour.board;

import soc.common.board.hexes.SeaHex;
import soc.common.board.ports.Port;
import soc.gwtClient.game.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.HexVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PortVisual;

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
            if (hexVisual.getHex().hasPort())
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
            if (hexVisual.getHex().hasPort())
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
