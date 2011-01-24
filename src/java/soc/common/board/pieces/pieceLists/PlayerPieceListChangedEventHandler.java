package soc.common.board.pieces.pieceLists;

import com.google.gwt.event.shared.EventHandler;

public interface PlayerPieceListChangedEventHandler<P> extends EventHandler
{
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<P> event);
}
