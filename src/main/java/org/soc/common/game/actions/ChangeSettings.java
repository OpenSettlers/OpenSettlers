package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

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
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour next(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionInGame opponentReceived(
          GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionInGame received(
          GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
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
