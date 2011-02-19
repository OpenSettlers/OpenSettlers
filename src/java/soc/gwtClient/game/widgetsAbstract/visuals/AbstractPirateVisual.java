package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.pieces.Pirate;
import soc.gwtClient.game.widgetsInterface.visuals.PirateVisual;

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
