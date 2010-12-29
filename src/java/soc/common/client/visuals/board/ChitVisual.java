package soc.common.client.visuals.board;

import soc.common.board.Chit;
import soc.common.client.visuals.AbstractPieceVisual;

public class ChitVisual extends AbstractPieceVisual implements IChitVisual
{
    protected Chit chit;
    final protected IBoardVisual parent;

    protected void updateChit()
    {
    }

    public ChitVisual(Chit chit, IBoardVisual parent)
    {
        this.chit = chit;
        this.parent = parent;
    }

    @Override
    public Chit getChit()
    {
        return chit;
    }

    @Override
    public IChitVisual setChit(Chit chit)
    {
        this.chit = chit;

        updateChit();

        return this;
    }
}
