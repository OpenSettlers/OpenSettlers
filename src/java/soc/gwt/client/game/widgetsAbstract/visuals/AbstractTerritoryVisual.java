package soc.gwt.client.game.widgetsAbstract.visuals;

import soc.common.board.territories.Territory;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.TerritoryVisual;

public abstract class AbstractTerritoryVisual extends AbstractPieceVisual
        implements TerritoryVisual
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

    /*
     * (non-Javadoc)
     * 
     * @seesoc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#
     * getTerritoryVisual()
     */
    @Override
    public TerritoryVisual getTerritoryVisual()
    {
        return this;
    }
}
