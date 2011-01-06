package soc.gwtClient.visuals.behaviour;

import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

/*
 * Represents a null behaviour, where a board does not have any 
 * behaviour attached
 */
public class NoBehaviour implements InteractionBehaviour
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
