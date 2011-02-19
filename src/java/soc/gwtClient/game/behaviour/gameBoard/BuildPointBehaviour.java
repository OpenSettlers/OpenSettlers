package soc.gwtClient.game.behaviour.gameBoard;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.gwtClient.game.widgetsInterface.visuals.GameBoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.IPointVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;

public abstract class BuildPointBehaviour implements GameBoardBehaviour
{
    List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

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
