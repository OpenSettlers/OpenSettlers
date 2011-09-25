package org.soc.common.presenters;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class HandAmounPresenter {
  public interface HandAmountView {
    public void setAmountHandCards(int amount);
  }

  public HandAmounPresenter(final HandAmountView view, GameWidget gameWidget,
          final GamePlayer player) {
    player.resources().addResourcesChangedHandler(new ResourcesChangedHandler() {
      @Override public void onResourcesChanged(ResourcesChangedEvent event) {
        view.setAmountHandCards(player.resources().size());
      }
    });
  }
}
