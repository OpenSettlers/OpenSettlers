package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.HexPoint;

public interface IPointVisual extends PieceVisual
{
    public HexPoint getHexPoint();

    public void addPieceVisual(PieceVisual pieceVisual);
}
