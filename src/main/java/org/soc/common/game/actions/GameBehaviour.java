package org.soc.common.game.actions;

import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public interface GameBehaviour {
  public void start(GameWidget gameWidget);
  public void finish();
  public boolean endsManually();

  public interface TradeFirst {
    public void onTraded();
    public void onCancelTrade();
  }

  public interface GameBehaviourCallback {
    public void done();
    public void cancel();
  }

  public class DefaultReceivedActionInGame implements GameBehaviour {
    private GameAction gameAction;
    private ActionDetailWidget playerDetailWidget;
    private GameWidget gameWidget;

    public DefaultReceivedActionInGame(GameWidget gameWidget, GameAction gameAction) {
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
