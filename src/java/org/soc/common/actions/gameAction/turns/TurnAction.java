package org.soc.common.actions.gameAction.turns;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.phases.turnPhase.TurnPhase;

public interface TurnAction extends GameAction
{
    public TurnPhase getTurnPhase();

    public TurnAction setTurnPhase(TurnPhase turnPhase);
}
