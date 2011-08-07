package org.soc.common.board.pieces.pieceLists;

import com.google.gwt.event.shared.EventHandler;

public interface PiecesChangedEventHandler extends EventHandler
{
    public void onPiecesChanged(PiecesChangedEvent list);
}
