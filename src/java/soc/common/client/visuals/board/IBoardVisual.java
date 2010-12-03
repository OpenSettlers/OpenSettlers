package soc.common.client.visuals.board;

import com.google.gwt.user.client.ui.Widget;

import soc.common.board.Board;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;

public interface IBoardVisual extends IPieceVisual
{
    public Board getBoard();
    public IInteractionBehaviour getInteractionBehaviour();
    public IBoardVisual setInteractionBehaviour(IInteractionBehaviour behaviour);
    public void showTerritories();
    public void hideTerritories();
    public Widget getWidget();
}
