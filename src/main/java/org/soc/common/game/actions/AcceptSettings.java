package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.main.*;

/** Opponents on the game table in the lobby phase, optionally must say "Ok, let's play!!" */
public class AcceptSettings extends AbstractGameAction {
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return false;
  }
  /** True when the game is in the lobby, and not yet started */
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isLobby();
  }
  @Override public void perform(Game game) {
    // Add the player to the list of players who accepted the settings
    LobbyGamePhase lobbyPhase = (LobbyGamePhase) game.phase();
    if (!lobbyPhase.settinggsAccepters().contains(player()))
      lobbyPhase.settinggsAccepters().add(player());
    super.perform(game);
  }
  @Override public String toDoMessage() {
    return I.get().actions().acceptSettingsToDo(player.user().name());
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour next(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour opponentReceived(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    // TODO Auto-generated method stub
    return null;
  }
}
