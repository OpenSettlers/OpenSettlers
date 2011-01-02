package soc.common.client.visuals.game;

import soc.common.board.pieces.Robber;
import soc.common.client.visuals.AbstractPieceVisual;

public class AbstractRobberVisual extends AbstractPieceVisual implements
        RobberVisual
{
    private Robber robber;

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