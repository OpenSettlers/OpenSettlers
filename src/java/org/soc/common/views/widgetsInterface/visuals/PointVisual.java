package org.soc.common.views.widgetsInterface.visuals;

import org.soc.common.board.layout.HexPoint;

public interface PointVisual extends PieceVisual
{
    public HexPoint getHexPoint();

    public void addPieceVisual(PieceVisual pieceVisual);
}
