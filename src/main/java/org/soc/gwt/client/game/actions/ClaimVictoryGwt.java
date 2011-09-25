package org.soc.gwt.client.game.actions;

import org.soc.common.game.actions.ClaimVictory.ClaimVictoryView;
import org.soc.common.game.actions.WantsClaimVictoryEvent;
import org.soc.common.game.actions.WantsClaimVictoryEvent.WantsClaimVictoryHandler;
import org.soc.common.game.actions.WantsEndTurnEvent;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class ClaimVictoryGwt implements ClaimVictoryView {
  PushButton btnClaimVictory = new PushButton(new Image(R.icons().claimVictory48()));
  private EventBus eventBus = new SimpleEventBus();

  public ClaimVictoryGwt() {
    super();
    btnClaimVictory.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsEndTurnEvent());
      }
    });
  }
  @Override public void enable() {
    btnClaimVictory.setEnabled(true);
  }
  @Override public void disable() {
    btnClaimVictory.setEnabled(true);
  }
  @Override public HandlerRegistration
          addWantsClaimVictoryHandler(WantsClaimVictoryHandler handler) {
    return eventBus.addHandler(WantsClaimVictoryEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
