package org.soc.common.board.pieces;

import com.google.gwt.event.shared.EventHandler;

public interface ArmyChangedEventHandler extends EventHandler
{
    public void onArmyChanged(ArmyChangedEvent event);
}
