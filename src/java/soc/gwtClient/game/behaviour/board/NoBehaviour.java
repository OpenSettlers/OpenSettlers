package soc.gwtClient.game.behaviour.board;

import soc.gwtClient.game.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;

/*
 * Represents a null behaviour, where a board does not have any 
 * behaviour attached
 */
public class NoBehaviour implements BoardBehaviour
{
    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
    }
}
