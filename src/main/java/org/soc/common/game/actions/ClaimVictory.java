package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

public class ClaimVictory extends AbstractTurnAction {
  @Override public Icon icon() {
    return new IconImpl(R.icons().claimVictory16(),
            R.icons().claimVictory32(), R.icons()
                    .claimVictory48());
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
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return true;
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns();
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game)) {
      return false;
    }
    if (player.victoryPoints().total() < game.board()
            .getBoardSettings().getVpToWin().vpToWin()) {
      invalidMessage = "Player does not have enough victory points to win";
      return false;
    }
    return true;
  }
  @Override public void perform(Game game) {
    game.actionsQueue()
            .enqueue((GameAction) new GamePhaseHasEnded()
                    .setSender(0),
                    true);
    super.perform(game);
  }
  @Override public String toDoMessage() {
    return I.get().actions().claimVictoryToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory) {
    return actionWidgetFactory.createClaimVictoryWidget();
  }
  @Override public ActionInGame opponentReceived(GameWidget gameWidget) {
    return new GameOverGameBehaviour(gameWidget, this);
  }
  @Override public ActionInGame received(GameWidget gameWidget) {
    return new GameOverGameBehaviour(gameWidget, this);
  }

  public static class GameOverGameBehaviour implements ActionInGame {
    ClaimVictory claimVictory;
    GameWidget gameWidget;

    public GameOverGameBehaviour(GameWidget gameWidget, ClaimVictory claimVictory) {
      super();
      this.claimVictory = claimVictory;
      this.gameWidget = gameWidget;
    }
    public ClaimVictory getClaimVictory() {
      return claimVictory;
    }
    @Override public void finish() {}
    @Override public void start(GameWidget gameWidget) {
      gameWidget.getGameOverDialog().update(this);
    }
    @Override public boolean endsManually() {
      return true;
    }
  }
}
