package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.TurnPhase;

public interface TurnAction extends GameAction {
  public TurnPhase getTurnPhase();
  public TurnAction setTurnPhase(TurnPhase turnPhase);

  public abstract class AbstractTurnAction extends AbstractGameAction implements TurnAction {
    protected TurnPhase turnPhasePerformed = null;

    @Override public TurnPhase getTurnPhase() {
      return turnPhasePerformed;
    }
    @Override public void perform(Game game) {
      super.perform(game);
    }
    @Override public TurnAction setTurnPhase(TurnPhase turnPhase) {
      this.turnPhasePerformed = turnPhase;
      return null;
    }
  }
}
