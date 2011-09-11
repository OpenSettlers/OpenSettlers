package org.soc.common.game.actions;

import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionOnGameBoard.BuildSideOnBoard;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;

public class BuildShip extends AbstractTurnAction
{
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
    return I.get().actions().buildShipToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour next(
          GameWidget gameWidget)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionInGame opponentReceived(
          GameWidget gameWidget)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionInGame received(
          GameWidget gameWidget)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour begin(
          GameWidget gameWidget)
  {
    // TODO Auto-generated method stub
    return null;
  }

  /* TODO: implement */
  public static class BuildShipBehaviour extends BuildSideOnBoard {
    @Override public GameAction gameAction() {
      return null;
    }
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
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
