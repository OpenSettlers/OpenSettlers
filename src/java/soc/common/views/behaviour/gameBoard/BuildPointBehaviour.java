package soc.common.views.behaviour.gameBoard;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.IPointVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public abstract class BuildPointBehaviour implements GameBoardBehaviour
{
    protected List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

    @Override
    public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual hexPointVisual = (IPointVisual) pieceVisual;
            hexPointVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual hexPointVisual = (IPointVisual) pieceVisual;
            hexPointVisual.setSelected(false);
        }
    }
}
