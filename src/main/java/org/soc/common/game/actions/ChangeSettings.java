package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.main.*;

public class ChangeSettings extends AbstractGameAction {
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isLobby();
  }
  @Override public void perform(Game game) {
    // Invalidate all other players
    LobbyGamePhase lobbyPhase = (LobbyGamePhase) game.phase();
    lobbyPhase.resetPlayersWhoAcceptedSettings(player());
    super.perform(game);
  }
  @Override public String toDoMessage() {
    return I.get().actions().noToDo();
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour next(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour opponentReceived(
          GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour received(
          GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
}
