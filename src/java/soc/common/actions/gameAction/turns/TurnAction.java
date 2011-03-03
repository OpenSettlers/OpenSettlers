package soc.common.actions.gameAction.turns;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.phases.turnPhase.TurnPhase;

public interface TurnAction extends GameAction
{
    public TurnPhase getTurnPhase();

    public TurnAction setTurnPhase(TurnPhase turnPhase);
}
