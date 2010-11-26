package soc.common.client.visuals.game;

import java.util.List;

import soc.common.board.HexPoint;
import soc.common.client.visuals.IPieceVisual;

public interface IHexPointsVisual extends IPieceVisual
{
    public void updatePoints(List<HexPoint> points);
}
