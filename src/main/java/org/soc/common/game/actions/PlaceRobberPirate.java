package org.soc.common.game.actions;

import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;

public class PlaceRobberPirate extends AbstractTurnAction
{
  private static final long serialVersionUID = 6371235501183992737L;

  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    // TODO Auto-generated method stub
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    // TODO Auto-generated method stub
    return false;
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().placeRobberToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Icon icon() {
    // TODO Auto-generated method stub
    return null;
  }
}
