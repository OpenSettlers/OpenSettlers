package soc.common.views.behaviour.gameBoard;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.PointVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

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
