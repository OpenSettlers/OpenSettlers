package soc.gwtClient.game.widgetsInterface.visuals;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
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
}
