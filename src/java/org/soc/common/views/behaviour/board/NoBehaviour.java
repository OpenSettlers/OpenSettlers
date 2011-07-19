package org.soc.common.views.behaviour.board;

import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;

/*
 * Represents a null behaviour, where a board does not have any 
 * behaviour attached
 */
public class NoBehaviour implements BoardBehaviour
{
    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        // Empty implementation
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        // Empty implementation
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        // Empty implementation
    }
}
