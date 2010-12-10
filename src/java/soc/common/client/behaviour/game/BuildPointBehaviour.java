package soc.common.client.behaviour.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.client.visuals.game.IPointVisual;

public class BuildPointBehaviour implements IGameBehaviour
{
    List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual hexPointVisual = (IPointVisual) pieceVisual;
            hexPointVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual hexPointVisual = (IPointVisual) pieceVisual;
            hexPointVisual.setSelected(false);
        }
    }

    @Override
    public void setNeutral(IGameBoardVisual visual)
    {
        visual.getHexPointsVisual().setVisible(false);
    }

    @Override
    public void start(IGameBoardVisual gameVisual)
    {
        gameVisual.getHexPointsVisual().updatePoints(possibleLocations);
    }

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        throw new RuntimeException();
    }

}
