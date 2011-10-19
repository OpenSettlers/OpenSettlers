package org.soc.common.game.actions;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;

public class TurnPhaseEnded extends AbstractGameAction
{
  @Override public boolean isValid(Game game)
  {
    return super.isValid(game);
  }
  @Override public String toDoMessage()
  {
    // TODO fix message
    return "End TurnPhase";
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return true;
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlayTurns();
  }
  @Override public void perform(Game game)
  {
    PlayTurnsGamePhase playTurns = (PlayTurnsGamePhase) game
            .phase();
    TurnPhase oldPhase = playTurns.turnPhase();
    if (game.advanceTurnPhase())
    {
      TurnPhase newPhase = playTurns.turnPhase();
      message = "Ended " + Models.name(oldPhase) + ", started "
              + Models.name(newPhase);
      super.perform(game);
    }
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}
