package soc.gwtClient.visuals.abstractVisuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexSide;

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
