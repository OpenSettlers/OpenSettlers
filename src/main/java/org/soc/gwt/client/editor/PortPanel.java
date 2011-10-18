package org.soc.gwt.client.editor;

import org.soc.common.game.*;
import org.soc.common.game.actions.ActionOnBoard.SetPortOnBoard;
import org.soc.common.utils.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.*;

import static org.soc.common.game.Ports.*;

public class PortPanel extends VerticalPanel {
  private SetPortOnBoard behaviour;
  private EventBus eventBus = new SafeEventBus();

  public HandlerRegistration addBehaviourChangedEventHandler(BehaviourChangedHandler handler) {
    return eventBus.addHandler(BehaviourChanged.TYPE, handler);
  }
  public PortPanel(SetPortOnBoard b) {
    super();
    behaviour = b;
    for (Port port : designPorts)
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
