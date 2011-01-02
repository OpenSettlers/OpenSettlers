package soc.common.client.behaviour.game;

import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.client.visuals.game.ISideVisual;

/*
 * Shows all possible sides for the user to select a side. 
 */
public class BuildSideBehaviour implements GameBehaviour
{

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        // /visual.getHexSidesVisual().setVisible(false);
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // gameVisual.getHexSidesVisual().setVisible(true);
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        throw new RuntimeException();
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof ISideVisual)
        {
            pieceVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof ISideVisual)
        {
            pieceVisual.setSelected(false);
        }
    }

}
