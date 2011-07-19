package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.board.Board;

import com.google.gwt.event.shared.GwtEvent;

public class BoardChangedEvent extends GwtEvent<BoardChangedEventHandler>
{
    public static Type<BoardChangedEventHandler> TYPE = new Type<BoardChangedEventHandler>();
    private Board newBoard;

    public BoardChangedEvent(Board newBoard)
    {
        super();
        this.newBoard = newBoard;
    }

    /**
     * @return the newBoard
     */
    public Board getNewBoard()
    {
        return newBoard;
    }

    @Override
    protected void dispatch(BoardChangedEventHandler handler)
    {
        handler.onBoardChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<BoardChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
