package soc.common.client.visuals.game;

import soc.common.board.HexPoint;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.PieceVisual;

public abstract class PointVisual extends PieceVisual implements IPointVisual
{
    private HexPoint hexPoint;

    public PointVisual(HexPoint hexPoint)
    {
        super();
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
