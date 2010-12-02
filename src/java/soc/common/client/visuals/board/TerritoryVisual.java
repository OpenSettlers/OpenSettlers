package soc.common.client.visuals.board;

import soc.common.board.Territory;
import soc.common.client.visuals.PieceVisual;

public abstract class TerritoryVisual extends PieceVisual implements ITerritoryVisual
{
    protected Territory territory;
    protected void updateTerritory() {}

    public TerritoryVisual(Territory territory)
    {
        this.territory=territory;
    }

    @Override
    public ITerritoryVisual setTerritory(Territory territory)
    {
        this.territory=territory;
        
        updateTerritory();
        
        return this;
    }
}
