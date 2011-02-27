package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.pieces.Pirate;
import soc.common.views.widgetsInterface.visuals.PirateVisual;

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

    /*
     * (non-Javadoc)
     * 
     * @seesoc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#
     * getPirateVisual()
     */
    @Override
    public PirateVisual getPirateVisual()
    {
        return this;
    }
}
