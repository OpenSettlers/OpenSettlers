package org.soc.common.board.pieces.abstractPieces;

import org.soc.common.board.layout.HexLocation;
import org.soc.common.board.pieces.MovedEventHandler;

import com.google.gwt.event.shared.HandlerRegistration;

public interface Moveable
{
    public HandlerRegistration addMovedEventHandler(MovedEventHandler handler);

    public void move(HexLocation newLocation);
}
