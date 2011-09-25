package org.soc.common.presenters;

import org.soc.common.game.ActionPerformedEvent;
import org.soc.common.game.ActionPerformedEvent.ActionPerformedHandler;
import org.soc.common.game.actions.Action;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class HistoryPresenter {
  public interface HistoryView {
    public void addAction(Action action);
  }

  private GameWidget gameWidget;
  private HistoryView view;

  public HistoryPresenter(GameWidget gameWidget) {
    this.gameWidget = gameWidget;
    gameWidget.game().log().addActionPerformedHandler(new ActionPerformedHandler() {
      @Override public void onActionPerformed(ActionPerformedEvent event) {
        view.addAction(event.getPerformedAction());
      }
    });
  }
}
