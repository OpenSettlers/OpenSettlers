package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionOnGameBoard.DisabledMap;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.I;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.ServerAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class HostStartsGame extends AbstractGameAction {
  private Game game;

  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
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
  @Override public void perform(Game game) {
    game.initialize();
    game.start();
    game.actionsQueue()
            .enqueue((GameAction) new GamePhaseHasEnded()
                    .setSender(0),
                    true);
    super.perform(game);
  }
  public Game getGame() {
    return game;
  }
  public HostStartsGame setGame(Game game) {
    this.game = game;
    return this;
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isLobby();
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return false;
  }
  @Override public String toDoMessage() {
    return I.get().actions().noToDo();
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public ActionInGame opponentReceived(GameWidget gameWidget) {
    return new StartGameGameBehaviour(gameWidget);
  }
  @Override public ActionInGame received(GameWidget gameWidget) {
    return new StartGameGameBehaviour(gameWidget);
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createHostStartsGameServerAction(this);
  }

  public static class StartGameGameBehaviour implements ActionInGame {
    DisabledMap disabledMap = new DisabledMap();
    GameWidget gameWidget;

    public StartGameGameBehaviour(GameWidget gameWidget) {
      super();
      this.gameWidget = gameWidget;
    }
    @Override public void finish() {
      disabledMap.setNeutral(gameWidget.boardVisualWidget()
              .boardVisual());
    }
    @Override public void start(GameWidget gameWidget) {
      disabledMap.start(gameWidget.boardVisualWidget().boardVisual());
    }
    @Override public boolean endsManually() {
      return false;
    }
  }
}
