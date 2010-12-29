package soc.common.client.visuals.board;

import soc.common.board.territories.Territory;
import soc.common.client.visuals.AbstractPieceVisual;

public abstract class TerritoryVisual extends AbstractPieceVisual implements
        ITerritoryVisual
{
    protected Territory territory;

    protected void updateTerritory()
    {
    }

    protected IBoardVisual parent;

    public TerritoryVisual(IBoardVisual boardVisual, Territory territory)
    {
        this.parent = boardVisual;
        this.territory = territory;
    }

    @Override
    public ITerritoryVisual setTerritory(Territory territory)
    {
        this.territory = territory;

        updateTerritory();

        return this;
    }
}
