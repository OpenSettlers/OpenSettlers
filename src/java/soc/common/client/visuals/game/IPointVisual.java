package soc.common.client.visuals.game;

import soc.common.board.HexPoint;
import soc.common.client.visuals.PieceVisual;

public interface IPointVisual extends PieceVisual
{
    public HexPoint getHexPoint();

    public void addPieceVisual(PieceVisual pieceVisual);
}
