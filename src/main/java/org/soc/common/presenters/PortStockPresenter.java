package org.soc.common.presenters;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.main.*;

import com.google.inject.*;

public class PortStockPresenter {
  public interface PortStockView {
    public void addPort(Port port);
    public void removePort(Port port);
  }

  @Inject public PortStockPresenter(final PortStockView view, GameWidget gameWidget,
          GamePlayer player) {
    player.ports().addAddedHandler(new Added<Port>() {
      @Override public void added(Port port) {
        view.addPort(port);
      }
    });
    player.ports().addRemovedHandler(new Removed<Port>() {
      @Override public void removed(Port port) {
        view.removePort(port);
      }
    });
  }
}
