package soc.common.client.visuals.board;

import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;

public abstract class PortVisual implements IPortVisual
{
    protected Port port;
    protected IBoardVisual parent;
    protected boolean selected = false;
    protected boolean visible = true;
    protected boolean enabled = true;
    protected boolean valid = false;

    protected void updatePort() {}
    protected void updateValid() {}
    
    public PortVisual(Port port, IBoardVisual parent)
    {
        this.port = port;
        this.parent=parent;
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

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public boolean isVisible()
    {
        return visible;
    }

    @Override
    public IPieceVisual setEnabled(boolean enabled)
    {
        this.enabled=enabled;

        return this;
    }

    @Override
    public IPieceVisual setSelected(boolean selected)
    {
        this.selected=selected;
        
        return this;
    }

    @Override
    public IPieceVisual setVisible(boolean visible)
    {
        this.visible=visible;

        return this;
    }

    @Override
    public boolean isValid()
    {
        return valid;
    }

    @Override
    public IPortVisual setValid(boolean valid)
    {
        this.valid=valid;
        
        // TODO Auto-generated method stub
        return this;
    }
   
}
