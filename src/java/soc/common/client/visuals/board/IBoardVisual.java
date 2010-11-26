package soc.common.client.visuals.board;

import soc.common.board.Board;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;

public interface IBoardVisual extends IPieceVisual
{
    public Board getBoard();
    public IInteractionBehaviour getInteractionBehaviour();
    public IBoardVisual setInteractionBehaviour(IInteractionBehaviour behaviour);
}
