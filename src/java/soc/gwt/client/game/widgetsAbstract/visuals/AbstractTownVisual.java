package soc.gwt.client.game.widgetsAbstract.visuals;

import soc.common.board.pieces.Town;
import soc.common.views.widgetsInterface.visuals.TownVisual;

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
     * soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getTownVisual
     * ()
     */
    @Override
    public TownVisual getTownVisual()
    {
        return this;
    }

}