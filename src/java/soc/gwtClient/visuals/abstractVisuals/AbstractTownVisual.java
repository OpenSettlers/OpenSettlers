package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.pieces.Town;

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

}
