package soc.common.board.pieces.abstractPieces;

import soc.common.board.layout.HexLocation;
import soc.common.board.pieces.MovedEventHandler;

import com.google.gwt.event.shared.HandlerRegistration;

public interface Moveable
{
    public HandlerRegistration addMovedEventHandler(MovedEventHandler handler);

    public void move(HexLocation newLocation);
}
