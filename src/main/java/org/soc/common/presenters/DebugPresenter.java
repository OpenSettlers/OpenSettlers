package org.soc.common.presenters;

import org.soc.common.game.actions.MessageFromServer;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class DebugPresenter {
  public interface DebugView {
    public void addError(MessageFromServer error);
  }

  private GameWidget gameWidget;
  private DebugView view;

  public void DebugPresenter(GameWidget gameWidget) {
    this.gameWidget = gameWidget;
    // TODO: handle some error event which is not yet defined
  }
}
