package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;

public class TurnPhaseEnded extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
  }
  @Override public String name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription()
  {
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
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}
