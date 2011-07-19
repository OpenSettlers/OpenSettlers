package org.soc.common.views.behaviour.gameBoard;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.board.layout.HexPoint;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PointVisual;


public abstract class BuildPointBehaviour implements GameBoardBehaviour
{
    protected List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

    @Override
    public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board)
    {
        PointVisual pointVisual = pieceVisual.getPointVisual();
        if (pointVisual != null)
        {
            PointVisual hexPointVisual = (PointVisual) pieceVisual;
            hexPointVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board)
    {
        PointVisual pointVisual = pieceVisual.getPointVisual();
        if (pointVisual != null)
        {
            PointVisual hexPointVisual = (PointVisual) pieceVisual;
            hexPointVisual.setSelected(false);
        }
    }
}
