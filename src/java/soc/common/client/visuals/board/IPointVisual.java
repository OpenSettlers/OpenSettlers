package soc.common.client.visuals.board;

import soc.common.board.HexPoint;
import soc.common.client.visuals.IPieceVisual;

public interface IPointVisual extends IPieceVisual
{
    public HexPoint getHexPoint();
}
