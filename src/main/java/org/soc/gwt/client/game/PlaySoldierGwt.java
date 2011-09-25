package org.soc.gwt.client.game;

import org.soc.common.game.DevelopmentCard.Soldier.SoldierView;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler;
import org.soc.common.internationalization.I;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class PlaySoldierGwt implements SoldierView {
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private Button btnPlay = new Button(I.get().constants().play());
  private EventBus eventBus = new SimpleEventBus();

  public PlaySoldierGwt() {
    rootPanel.setSpacing(5);
    rootPanel.add(new Image(R.icons.soldier48()));
    rootPanel.add(new Label(I.get().constants().soldier()));
    rootPanel.add(btnPlay);
    btnPlay.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsPlayDevelopmentCardEvent());
      }
    });
  }
  @Override public void enable() {
    btnPlay.setEnabled(true);
  }
  @Override public void disable() {
    btnPlay.setEnabled(false);
  }
  @Override public HandlerRegistration addWantsPlayDevelopmentCardHandler(
          WantsPlayDevelopmentCardHandler handler) {
    return eventBus.addHandler(WantsPlayDevelopmentCardEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
