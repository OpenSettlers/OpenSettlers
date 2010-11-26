package soc.common.client.visuals.board;

import soc.common.board.Territory;
import soc.common.client.visuals.IPieceVisual;

public class TerritoryVisual implements ITerritoryVisual
{
    protected Territory territory;
    
    public TerritoryVisual(Territory territory)
    {
        this.territory=territory;
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
        return this;
    }

    @Override
    public ITerritoryVisual setTerritory(Territory territory)
    {
        this.territory=territory;
        
        updateTerritory();
        // TODO Auto-generated method stub
        return this;
    }

    protected void updateTerritory()
    {
        
    }

}
