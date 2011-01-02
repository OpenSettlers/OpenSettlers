package soc.common.client.visuals.game;

import soc.common.board.pieces.Town;
import soc.common.client.visuals.AbstractPieceVisual;

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
