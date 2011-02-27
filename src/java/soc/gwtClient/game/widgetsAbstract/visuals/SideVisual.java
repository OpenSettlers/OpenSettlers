package soc.gwtClient.game.widgetsAbstract.visuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexSide;
import soc.common.views.widgetsInterface.visuals.ISideVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#getSideVisual
     * ()
     */
    @Override
    public ISideVisual getSideVisual()
    {
        return this;
    }

}
