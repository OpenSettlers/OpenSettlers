package soc.common.client.visuals.game;

import soc.common.board.HexPoint;
import soc.common.client.visuals.IPieceVisual;

public interface IPointVisual extends IPieceVisual
{
    public HexPoint getHexPoint();
}
