package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.board.pieces.Robber;
import org.soc.common.views.widgetsInterface.visuals.RobberVisual;

public class AbstractRobberVisual extends AbstractPieceVisual implements
        RobberVisual
{
    protected Robber robber;

    public AbstractRobberVisual(Robber robber)
    {
        super();
        this.robber = robber;
    }

    @Override
    public Robber getRober()
    {
        return robber;
    }

    /*
     * (non-Javadoc)
     * 
     * @seesoc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#
     * getRobberVisual()
     */
    @Override
    public RobberVisual getRobberVisual()
    {
        return this;
    }
}