package soc.common.client.visuals.game;

import soc.common.board.HexSide;
import soc.common.client.visuals.PieceVisual;

public abstract class SideVisual extends PieceVisual implements ISideVisual
{
    private HexSide hexSide;
    
    public SideVisual(HexSide hexSide)
    {
        super();
        this.hexSide = hexSide;
    }

    @Override
    public HexSide getHexSide()
    {
        return hexSide;
    }
}
