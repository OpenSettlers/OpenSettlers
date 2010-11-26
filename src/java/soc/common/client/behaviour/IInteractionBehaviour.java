package soc.common.client.behaviour;

import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;

public interface IInteractionBehaviour
{
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board);
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board);
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board);
}
