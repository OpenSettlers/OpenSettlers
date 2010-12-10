package soc.common.client.behaviour.game;

import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.client.visuals.game.IHexSideVisual;

public class BuildSideBehaviour implements IGameBehaviour
{

    @Override
    public void setNeutral(IGameBoardVisual visual)
    {
        visual.getHexSidesVisual().setVisible(false);
    }

    @Override
    public void start(IGameBoardVisual gameVisual)
    {
        gameVisual.getHexSidesVisual().setVisible(true);
    }

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        throw new RuntimeException();
    }

    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexSideVisual)
        {
            pieceVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexSideVisual)
        {
            pieceVisual.setSelected(false);
        }
    }

}