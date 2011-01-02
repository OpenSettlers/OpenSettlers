package soc.common.client.visuals.game;

import soc.common.board.pieces.Pirate;
import soc.common.client.visuals.AbstractPieceVisual;

public class AbstractPirateVisual extends AbstractPieceVisual implements
        PirateVisual
{
    private Pirate pirate;

    public AbstractPirateVisual(Pirate pirate)
    {
        super();
        this.pirate = pirate;
    }

    @Override
    public Pirate getPirate()
    {
        return pirate;
    }
}
