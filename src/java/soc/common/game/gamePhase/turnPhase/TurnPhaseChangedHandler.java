package soc.common.game.gamePhase.turnPhase;

import com.google.gwt.event.shared.EventHandler;

public interface TurnPhaseChangedHandler extends EventHandler
{
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event);
}
