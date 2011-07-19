package soc.gwt.client.game.widgetsAbstract.visuals;

import soc.common.board.ports.Port;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.PortVisual;

public abstract class AbstractPortVisual extends AbstractPieceVisual implements
        PortVisual
{
    protected Port port;
    protected BoardVisual parent;
    protected boolean valid = false;

    protected void updatePort()
    {
    }

    protected void updateValid()
    {
    }

    public AbstractPortVisual(Port port, BoardVisual parent)
    {
        this.port = port;
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IPortVisual#isValid()
     */
    @Override
    public boolean isValid()
    {
        return valid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IPortVisual#setValid(boolean)
     */
    @Override
    public PortVisual setValid(boolean valid)
    {
        this.valid = valid;

        updateValid();

        return this;
    }

    @Override
    public Port getPort()
    {
        return port;
    }

    @Override
    public PortVisual setPort(Port port)
    {
        this.port = port;

        updatePort();

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getPortVisual
     * ()
     */
    @Override
    public PortVisual getPortVisual()
    {
        return this;
    }
}