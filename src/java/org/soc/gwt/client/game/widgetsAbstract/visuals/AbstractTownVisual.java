package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.board.pieces.Town;
import org.soc.common.views.widgetsInterface.visuals.TownVisual;

public class AbstractTownVisual extends AbstractPieceVisual implements
        TownVisual
{
    protected Town town;

    @Override
    public Town getTown()
    {
        return town;
    }

    public AbstractTownVisual(Town town)
    {
        super();
        this.town = town;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getTownVisual
     * ()
     */
    @Override
    public TownVisual getTownVisual()
    {
        return this;
    }

}
