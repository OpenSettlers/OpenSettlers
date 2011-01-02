package soc.common.client.visuals.board;

import soc.common.board.territories.Territory;
import soc.common.client.visuals.AbstractPieceVisual;

public abstract class AbstractTerritoryVisual extends AbstractPieceVisual implements
        TerritoryVisual
{
    protected Territory territory;

    protected void updateTerritory()
    {
    }

    protected BoardVisual parent;

    public AbstractTerritoryVisual(BoardVisual boardVisual, Territory territory)
    {
        this.parent = boardVisual;
        this.territory = territory;
    }

    @Override
    public TerritoryVisual setTerritory(Territory territory)
    {
        this.territory = territory;

        updateTerritory();

        return this;
    }
}
