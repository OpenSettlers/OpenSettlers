package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.ActionOnGameBoard.BuildSideOnBoard;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.visuals.*;

public class BuildShip extends AbstractTurnAction implements TurnAction
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
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory)
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
  @Override public GameBehaviour opponentReceived(
          GameWidget gameWidget)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour received(
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

  @Override public Name name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Icon icon() {
    // TODO Auto-generated method stub
    return null;
  }
}
