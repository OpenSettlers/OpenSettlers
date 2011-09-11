package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.game.board.Board;
import org.soc.common.views.widgetsInterface.generic.BoardChangedEvent.HasBoardChangedHandlers;

import com.google.gwt.user.client.ui.IsWidget;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/** Makes the user pick a board from a list of available boards. */
public interface BoardPickerWidget extends IsWidget, HasBoardChangedHandlers {
  /* Returns the board instance which currently is selected, or null if no board is selected. */
  public Board selectedBoard();
  /* Returns true when a board is selected */
  public boolean hasBoardSelected();
  public void selectFirst();

  @GenEvent public class BoardChanged {
    @Order(0) Board newBoard;
  }
}
