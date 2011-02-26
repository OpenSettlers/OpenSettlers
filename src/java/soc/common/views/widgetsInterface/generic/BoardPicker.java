package soc.common.views.widgetsInterface.generic;

import soc.common.board.Board;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

/*
 * Makes the user pick a board from a list of available boards.
 */
public interface BoardPicker extends IsWidget
{
    /*
     * Returns the board instance which currently is selected, or null if no
     * board is selected.
     */
    public Board getSelectedBoard();

    /*
     * Returns true when a board is selected
     */
    public boolean hasBoardSelected();

    /*
     * Enable listeners to get notified when the selected board changed
     */
    public HandlerRegistration addBoardChangedHandler(
            BoardChangedEventHandler handler);
}
