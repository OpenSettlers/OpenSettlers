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

/** Opponents on the game table in the lobby phase, optionally must say "Ok, let's play!!" */
public class AcceptSettings extends AbstractGameAction {
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return false;
  }
  /** Returns true when the game is in the lobby */
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isLobby();
  }
  @Override public void perform(Game game) {
    // Add the player to the list of players who accepted the settings
    LobbyGamePhase lobbyPhase = (LobbyGamePhase) game.phase();
    if (!lobbyPhase.playersWhoAcceptedSettings().contains(player())) {
      lobbyPhase.playersWhoAcceptedSettings().add(player());
    }
    super.perform(game);
  }
  @Override public String toDoMessage() {
    return I.get().actions().acceptSettingsToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour next(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionInGame opponentReceived(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionInGame received(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String name() {
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
