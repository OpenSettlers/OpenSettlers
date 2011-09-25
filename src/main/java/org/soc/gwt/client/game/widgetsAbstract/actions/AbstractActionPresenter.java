package org.soc.gwt.client.game.widgetsAbstract.actions;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.Action.ActionPresenter;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public abstract class AbstractActionPresenter implements ActionPresenter {
  protected GamePlayer player;
  protected GameWidget gameWidget;
  protected boolean onTurn;
  protected boolean enabled;

  protected abstract void updateEnabled();
  public AbstractActionPresenter(GameWidget gameWidget, GamePlayer player) {
    this.player = player;
    this.gameWidget = gameWidget;
  }
  @Override public GamePlayer getPlayer() {
    return player;
  }
  @Override public ActionPresenter setEnabled(boolean enabled) {
    this.enabled = enabled;
    updateEnabled();
    return this;
  }
}
