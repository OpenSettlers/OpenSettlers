package soc.common.client.behaviour.game;

import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.client.visuals.game.ISideVisual;

/*
 * Shows all possible sides for the user to select a side. 
 */
public abstract class BuildSideBehaviour implements GameBehaviour
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
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        throw new RuntimeException();
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof ISideVisual)
        {
            pieceVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof ISideVisual)
        {
            pieceVisual.setSelected(false);
        }
    }
}
