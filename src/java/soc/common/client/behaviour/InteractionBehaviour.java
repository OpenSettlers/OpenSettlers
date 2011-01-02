package soc.common.client.behaviour;

import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;

public interface InteractionBehaviour
{
    public void clicked(PieceVisual pieceVisual, BoardVisual board);
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board);
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board);
}
