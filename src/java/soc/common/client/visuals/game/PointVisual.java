package soc.common.client.visuals.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.client.visuals.AbstractPieceVisual;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;

public abstract class PointVisual extends AbstractPieceVisual implements
        IPointVisual
{
    protected HexPoint hexPoint;
    protected BoardVisual parent;
    protected List<PieceVisual> pieceVisuals = new ArrayList<PieceVisual>();

    public PointVisual(BoardVisual parent, HexPoint hexPoint)
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
