package soc.common.client.visuals.game;

import soc.common.board.HexSide;
import soc.common.client.visuals.AbstractPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;

public abstract class SideVisual extends AbstractPieceVisual implements
        ISideVisual
{
    private HexSide hexSide;
    protected IBoardVisual parent;

    public SideVisual(IBoardVisual parent, HexSide hexSide)
    {
        super();
        this.parent = parent;
        this.hexSide = hexSide;
    }

    @Override
    public HexSide getHexSide()
    {
        return hexSide;
    }
}
