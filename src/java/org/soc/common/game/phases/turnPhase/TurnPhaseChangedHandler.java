package org.soc.common.game.phases.turnPhase;

import com.google.gwt.event.shared.EventHandler;

public interface TurnPhaseChangedHandler extends EventHandler
{
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event);
}
