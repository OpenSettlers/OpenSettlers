package org.soc.common.game.actions;

import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public interface ActionInGame extends GameBehaviour {
  public boolean endsManually();

  public class DefaultOpponentReceivedBehaviour implements ActionInGame {
    private GameAction gameAction;
    private ActionDetailWidget playerDetailWidget;
    private GameWidget gameWidget;

    public DefaultOpponentReceivedBehaviour(GameWidget gameWidget, GameAction gameAction) {
      super();
      this.gameWidget = gameWidget;
      this.gameAction = gameAction;
    }
    @Override public boolean endsManually() {
      return false;
    }
    @Override public void finish() {
      gameWidget.detailWidgets().hideCurrentWidget();
    }
    @Override public void start(GameWidget gameWidget) {
      ActionDetailWidgetFactory factory = gameWidget.clientFactory().actionDetailWidgetFactory();
      ActionDetailWidget actionDetailWidget = gameAction.createDetailWidget(factory);
      if (actionDetailWidget != null)
        gameWidget.detailWidgets().showActionWidget(actionDetailWidget);
    }
  }
}
