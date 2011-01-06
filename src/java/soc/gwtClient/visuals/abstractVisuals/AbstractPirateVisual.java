package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.pieces.Pirate;

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
