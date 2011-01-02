package soc.common.client.behaviour.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.client.visuals.game.IPointVisual;

public class BuildPointBehaviour implements GameBehaviour
{
    List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual hexPointVisual = (IPointVisual) pieceVisual;
            hexPointVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual hexPointVisual = (IPointVisual) pieceVisual;
            hexPointVisual.setSelected(false);
        }
    }

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        // visual.getHexPointsVisual().setVisible(false);
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // gameVisual.getHexPointsVisual().updatePoints(possibleLocations);
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        throw new RuntimeException();
    }

}
