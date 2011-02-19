package soc.gwtClient.game.widgetsInterface.visuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexSide;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual;

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
