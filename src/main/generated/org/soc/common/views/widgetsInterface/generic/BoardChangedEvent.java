package org.soc.common.views.widgetsInterface.generic;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class BoardChangedEvent extends GwtEvent<BoardChangedEvent.BoardChangedHandler> { 

  public interface HasBoardChangedHandlers extends HasHandlers {
    HandlerRegistration addBoardChangedHandler(BoardChangedHandler handler);
  }

  public interface BoardChangedHandler extends EventHandler {
    public void onBoardChanged(BoardChangedEvent event);
  }

  private static final Type<BoardChangedHandler> TYPE = new Type<BoardChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.board.Board newBoard) {
    source.fireEvent(new BoardChangedEvent(newBoard));
  }

  public static Type<BoardChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.board.Board newBoard;

  public BoardChangedEvent(org.soc.common.game.board.Board newBoard) {
    this.newBoard = newBoard;
  }

  protected BoardChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<BoardChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.board.Board getNewBoard() {
    return newBoard;
  }

  @Override
  protected void dispatch(BoardChangedHandler handler) {
    handler.onBoardChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    BoardChangedEvent other = (BoardChangedEvent) obj;
    if (newBoard == null) {
      if (other.newBoard != null)
        return false;
    } else if (!newBoard.equals(other.newBoard))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newBoard == null ? 1 : newBoard.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "BoardChangedEvent["
                 + newBoard
    + "]";
  }
}
