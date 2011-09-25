package org.soc.gwt.client.game.actions;

import org.soc.common.game.actions.EndTurn.EndTurnView;
import org.soc.common.game.actions.WantsEndTurnEvent;
import org.soc.common.game.actions.WantsEndTurnEvent.WantsEndTurnHandler;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class EndTurnGwt implements EndTurnView {
  private PushButton btnEndTurn = new PushButton(new Image(R.icons().endTurn32()));
  private EventBus eventBus = new SimpleEventBus();

  public EndTurnGwt() {
    super();
    btnEndTurn.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsEndTurnEvent());
      }
    });
  }
  @Override public HandlerRegistration addWantsEndTurnHandler(WantsEndTurnHandler handler) {
    return eventBus.addHandler(WantsEndTurnEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public void enable() {
    btnEndTurn.setEnabled(true);
  }
  @Override public void disable() {
    btnEndTurn.setEnabled(false);
  }
}
