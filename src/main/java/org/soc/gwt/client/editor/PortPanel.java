package org.soc.gwt.client.editor;

import org.soc.common.game.Port;
import org.soc.common.game.PortList;
import org.soc.common.game.Port.ClayPort;
import org.soc.common.game.Port.OrePort;
import org.soc.common.game.Port.RandomPort;
import org.soc.common.game.Port.SheepPort;
import org.soc.common.game.Port.ThreeToOnePort;
import org.soc.common.game.Port.TimberPort;
import org.soc.common.game.Port.WheatPort;
import org.soc.common.game.actions.ActionOnBoard.SetPortOnBoard;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PortPanel extends VerticalPanel {
  private SetPortOnBoard behaviour;
  private static PortList ports = new PortList();
  static {
    ports.add(new ThreeToOnePort());
    ports.add(new WheatPort());
    ports.add(new TimberPort());
    ports.add(new OrePort());
    ports.add(new SheepPort());
    ports.add(new ClayPort());
    ports.add(new RandomPort());
  }
  private EventBus eventBus = new SimpleEventBus();

  public HandlerRegistration addBehaviourChangedEventHandler(BehaviourChangedHandler handler) {
    return eventBus.addHandler(BehaviourChanged.TYPE, handler);
  }
  public PortPanel(SetPortOnBoard b) {
    super();
    behaviour = b;
    for (Port port : ports)
      add(new PortButton(port));
  }

  private class PortButton extends PushButton {
    public PortButton(final Port port) {
      super(new Image(R.mediumIcon(port)));
      addClickHandler(new ClickHandler() {
        @Override public void onClick(ClickEvent event) {
          behaviour.setPort(port);
          fireEvent(new BehaviourChanged(behaviour));
        }
      });
    }
  }
}
