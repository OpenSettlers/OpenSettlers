package soc.common.client.visuals.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexSide;
import soc.common.client.visuals.AbstractPieceVisual;
import soc.common.client.visuals.PieceVisual;

public abstract class SideVisual extends AbstractPieceVisual implements
        ISideVisual
{
    private HexSide hexSide;
    protected List<PieceVisual> pieceVisuals = new ArrayList<PieceVisual>();

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
