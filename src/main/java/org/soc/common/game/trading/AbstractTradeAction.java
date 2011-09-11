package org.soc.common.game.trading;

import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;

public abstract class AbstractTradeAction extends AbstractTurnAction
{
  private static final long serialVersionUID = 7078710036730939921L;

  @Override public String toDoMessage()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return turnPhase.isTrading();
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlayTurns();
  }
}
