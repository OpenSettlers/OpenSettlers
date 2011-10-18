package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.*;

public class TurnPhaseEnded extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
  }
  @Override public Name name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
    // TODO Auto-generated method stub
    return null;
  }
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
      message = "Ended " + oldPhase.name() + ", started "
              + newPhase.name();
      super.perform(game);
    }
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}
