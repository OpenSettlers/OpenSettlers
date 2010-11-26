package soc.common.client.behaviour.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.game.IGameVisual;
import soc.common.client.visuals.game.IHexPointVisual;

public class BuildPointBehaviour implements IGameBehaviour
{
    List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexPointVisual)
        {
            IHexPointVisual hexPointVisual = (IHexPointVisual) pieceVisual;
            hexPointVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexPointVisual)
        {
            IHexPointVisual hexPointVisual = (IHexPointVisual) pieceVisual;
            hexPointVisual.setSelected(false);
        }
    }

    @Override
    public void setNeutral(IGameVisual visual)
    {
        visual.getHexPointsVisual().setVisible(false);
    }

    @Override
    public void start(IGameVisual gameVisual)
    {
        gameVisual.getHexPointsVisual().updatePoints(possibleLocations);
    }

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        throw new RuntimeException();
    }

}
