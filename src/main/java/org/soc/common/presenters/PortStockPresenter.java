package org.soc.common.presenters;

import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.Port;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.inject.Inject;

public class PortStockPresenter {
  public interface PortStockView {
    public void addPort(Port port);
    public void removePort(Port port);
  }

  @Inject public PortStockPresenter(final PortStockView view, GameWidget gameWidget,
          GamePlayer player) {
    player.ports().addPortListChangedHandler(new PortListChangedHandler() {
      @Override public void onPortListChanged(PortListChangedEvent event) {
        if (event.getAddedPort() != null)
          view.addPort(event.getAddedPort());
        if (event.getRemovedPort() != null)
          view.removePort(event.getRemovedPort());
      }
    });
  }
}
