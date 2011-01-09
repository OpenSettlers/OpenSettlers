package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.pieces.Robber;

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

}