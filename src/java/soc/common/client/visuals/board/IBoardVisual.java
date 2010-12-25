package soc.common.client.visuals.board;

import soc.common.board.Board;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;

import com.google.gwt.user.client.ui.IsWidget;

public interface IBoardVisual extends IPieceVisual, IsWidget
{
    public boolean isEnabled();

    public Board getBoard();

    public IInteractionBehaviour getCurrentBehaviour();

    public IBoardVisual setInteractionBehaviour(IInteractionBehaviour behaviour);

    /*
     * Toggle visbility of territory to show
     */
    public void showTerritories();

    /*
     * Toggle visbility of territory to hide
     */
    public void hideTerritories();
}
