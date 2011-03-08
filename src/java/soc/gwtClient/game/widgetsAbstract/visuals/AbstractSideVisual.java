package soc.gwtClient.game.widgetsAbstract.visuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.layout.HexSide;
import soc.common.views.widgetsInterface.visuals.SideVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public abstract class AbstractSideVisual extends AbstractPieceVisual implements
        SideVisual
{
    private HexSide hexSide;
    protected List<PieceVisual> pieceVisuals = new ArrayList<PieceVisual>();

    public AbstractSideVisual(HexSide hexSide)
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
    public SideVisual getSideVisual()
    {
        return this;
    }

}
