package soc.common.client.visuals.board;

import soc.common.board.ports.Port;
import soc.common.client.visuals.AbstractPieceVisual;

public abstract class PortVisual extends AbstractPieceVisual implements IPortVisual
{
    protected Port port;
    protected IBoardVisual parent;
    protected boolean valid = false;

    protected void updatePort() {}
    protected void updateValid() {}
    
    public PortVisual(Port port, IBoardVisual parent)
    {
        this.port = port;
        this.parent=parent;
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.IPortVisual#isValid()
     */
    @Override
    public boolean isValid()
    {
        return valid;
    }
    
    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.IPortVisual#setValid(boolean)
     */
    @Override
    public IPortVisual setValid(boolean valid)
    {
        this.valid=valid;

        updateValid();

        return this;
    }
    
    @Override
    public Port getPort()
    {
        return port;
    }

    @Override
    public IPortVisual setPort(Port port)
    {
        this.port=port;
        
        updatePort();

        return this;
    }   
}
