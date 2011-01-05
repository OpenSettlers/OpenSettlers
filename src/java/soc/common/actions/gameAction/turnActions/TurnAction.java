package soc.common.actions.gameAction.turnActions;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public interface TurnAction extends GameAction
{
    public TurnPhase getTurnPhase();

    public TurnAction setTurnPhase(TurnPhase turnPhase);
}
