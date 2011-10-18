package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.ActionOnGameBoard.DisabledMap;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.server.actions.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;

public class HostStartsGame extends AbstractGameAction {
  private Game game;

  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
  }
  @Override public Name name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
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
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public GameBehaviour opponentReceived(GameWidget gameWidget) {
    return new StartGameGameBehaviour(gameWidget);
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    return new StartGameGameBehaviour(gameWidget);
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createHostStartsGame(this);
  }

  public static class StartGameGameBehaviour implements GameBehaviour {
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
