package org.soc.common.game.actions;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;

/** Announces a gamephase which has been ended
 * TODO: refactor into event */
public class GamePhaseHasEnded extends AbstractGameAction {
  private GamePhase endedGamePhase;
  private GamePhase newPhase;

  public GamePhase getNewPhase() {
    return newPhase;
  }
  @Override public void perform(Game game) {
    endedGamePhase = game.phase();
    game.advanceGamePhase();
    newPhase = game.phase();
    // TODO: fix message
    message = Models.name(endedGamePhase) + " has ended, entering"
            + Models.name(newPhase);
    super.perform(game);
  }
  public GamePhase getEndedGamePhase() {
    return endedGamePhase;
  }
  public GamePhaseHasEnded setEndedGamePhase(GamePhase endedGamePhase) {
    this.endedGamePhase = endedGamePhase;
    return this;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return true;
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return true;
  }
  @Override public String toDoMessage() {
    return I.get().actions().noToDo();
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}