package org.soc.common.presenters;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.Resource;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class HandAmounPresenter {
  private HandAmountView view;
  private GamePlayer player;

  public interface HandAmountView {
    public void setAmountHandCards(int amount);
  }

  public HandAmounPresenter(final HandAmountView view, GameWidget gameWidget,
          final GamePlayer player) {
    this.view = view;
    this.player = player;
    player.resources().addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        update();
      }
    });
    player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        update();
      }
    });
  }
  private void update() {
    view.setAmountHandCards(player.resources().size());
  }
}
