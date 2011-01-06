package soc.gwtClient.visuals.behaviour.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.IPointVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

public abstract class BuildPointBehaviour implements GameBehaviour
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

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        throw new RuntimeException();
    }

}
