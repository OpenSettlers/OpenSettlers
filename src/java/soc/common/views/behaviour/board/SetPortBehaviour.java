package soc.common.views.behaviour.board;

import soc.common.board.hexes.AbstractHex;
import soc.common.board.ports.Port;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.HexVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.PortVisual;

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
        PortVisual portVisual = pieceVisual.getPortVisual();
        if (portVisual != null)
        {
            AbstractHex seaHex = (AbstractHex) currentHexVisual.getHex();

            Port p = port.copy();
            p.setRotationPosition(portVisual.getPort().getRotationPosition());
            p.setHexLocation(seaHex.getLocation());
            seaHex.setPort(p);
        }
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        HexVisual hexVisual = pieceVisual.getHexVisual();
        if (hexVisual != null && hexVisual.getHex().hasPort())
        {
            hexVisual.setSelected(true);
            hexVisual.getPortPossibilitiesVisual().setVisible(true);
            hexVisual.getPortPossibilitiesVisual().updatePossibilities();
            currentHexVisual = hexVisual;
        }

        PortVisual portVisual = pieceVisual.getPortVisual();
        if (portVisual != null)
            portVisual.setSelected(true);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        HexVisual hexVisual = pieceVisual.getHexVisual();
        if (hexVisual != null && hexVisual.getHex().hasPort())
        {
            hexVisual.setSelected(false);
            hexVisual.getPortPossibilitiesVisual().setVisible(false);
            currentHexVisual = null;
        }

        PortVisual portVisual = pieceVisual.getPortVisual();
        if (portVisual != null)
            portVisual.setSelected(false);
    }

}
