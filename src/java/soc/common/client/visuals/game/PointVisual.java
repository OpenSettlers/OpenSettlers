package soc.common.client.visuals.game;

import soc.common.board.HexPoint;
import soc.common.client.visuals.AbstractPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;

public abstract class PointVisual extends AbstractPieceVisual implements
        IPointVisual
{
    protected HexPoint hexPoint;
    protected IBoardVisual parent;

    public PointVisual(IBoardVisual parent, HexPoint hexPoint)
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
