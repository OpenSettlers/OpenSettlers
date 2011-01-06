package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.territories.Territory;

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
