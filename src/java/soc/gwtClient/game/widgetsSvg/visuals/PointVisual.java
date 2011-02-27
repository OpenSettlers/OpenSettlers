package soc.gwtClient.game.widgetsSvg.visuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.IPointVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual;

public abstract class PointVisual extends AbstractPieceVisual implements
        IPointVisual
{
    protected HexPoint hexPoint;
    protected GameBoardVisual parent;
    protected List<PieceVisual> pieceVisuals = new ArrayList<PieceVisual>();

    public PointVisual(GameBoardVisual parent, HexPoint hexPoint)
    {
        super();
        this.parent = parent;
        this.hexPoint = hexPoint;
    }

    /**
     * @return the hexPoint
     */
    public HexPoint getHexPoint()
    {
        return hexPoint;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#getPointVisual
     * ()
     */
    @Override
    public IPointVisual getPointVisual()
    {
        return this;
    }

}
