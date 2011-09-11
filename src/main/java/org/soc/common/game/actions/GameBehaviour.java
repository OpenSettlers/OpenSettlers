package org.soc.common.game.actions;

import org.soc.common.views.widgetsInterface.main.GameWidget;

public interface GameBehaviour {
  public void start(GameWidget gameWidget);
  public void finish();

  public interface TradeFirst {
    public void onTraded();
    public void onCancelTrade();
  }

  public interface GameBehaviourCallback {
    public void done();
    public void cancel();
  }
}
