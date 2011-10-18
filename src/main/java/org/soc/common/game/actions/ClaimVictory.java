package org.soc.common.game.actions;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsClaimVictoryEvent.HasWantsClaimVictoryHandlers;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.dispatch.annotation.*;

public class ClaimVictory extends AbstractTurnAction {
  @Override public Icon icon() {
    return new IconImpl(R.icons().claimVictory16(),
            R.icons().claimVictory32(), R.icons()
                    .claimVictory48());
  }
  @Override public Name name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
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
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory) {
    return actionWidgetFactory.createClaimVictoryWidget();
  }
  @Override public GameBehaviour opponentReceived(GameWidget gameWidget) {
    return new GameOverGameBehaviour(gameWidget, this);
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    return new GameOverGameBehaviour(gameWidget, this);
  }

  public static class GameOverGameBehaviour implements GameBehaviour {
    ClaimVictory claimVictory;
    GameWidget gameWidget;

    public GameOverGameBehaviour(GameWidget gameWidget, ClaimVictory claimVictory) {
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

  public interface ClaimVictoryView extends HasWantsClaimVictoryHandlers {
    @GenEvent public class WantsClaimVictory {}

    public void enable();
    public void disable();
  }

  public static class ClaimVictoryBitmapWidget extends AbstractActionPresenter implements
          VictoryPointsChangedHandler
  {
    private ClaimVictoryView view;

    public ClaimVictoryBitmapWidget(GameWidget gameWidget, GamePlayer player) {
      super(gameWidget, player);
      player.victoryPoints().addAddedHandler(new Added<VictoryPointItem>() {
        @Override public void added(VictoryPointItem item) {
          checkEnabled();
        }
      });
      player.victoryPoints().addRemovedHandler(new Removed<VictoryPointItem>() {
        @Override public void removed(VictoryPointItem item) {
          checkEnabled();
        }
      });
    }
    @Override public Widget asWidget() {
      return (Widget) view;
    }
    @Override protected void updateEnabled() {
      checkEnabled();
    }
    private void checkEnabled()
    {
      if (enabled
              && player.isOnTurn()
              && gameWidget.game().gameSettings()
                      .getBoardSettings().getVpToWin()
                      .vpToWin() <= player
                      .victoryPoints().total())
      {
        view.enable();
        return;
      }
      view.disable();
    }
    @Override public void onVictoryPointsChanged(VictoryPointsChangedEvent event) {
      checkEnabled();
    }
  }
}
