package soc.common.client.visuals.board;

import soc.common.board.Chit;
import soc.common.client.visuals.IPieceVisual;

public class ChitVisual implements IChitVisual
{
    protected Chit chit;
    final protected IBoardVisual parent;
    
    public ChitVisual(Chit chit, IBoardVisual parent)
    {
        this.chit=chit;
        this.parent=parent;
    }

    @Override
    public Chit getChit()
    {
        // TODO Auto-generated method stub
        return chit;
    }

    @Override
    public IChitVisual setChit(Chit chit)
    {
        this.chit=chit;
        
        updateChit();
        
        // TODO Auto-generated method stub
        return this;
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
        // TODO Auto-generated method stub
        return null;
    }

    protected void updateChit()
    {
        // TODO Auto-generated method stub
        
    }

}
