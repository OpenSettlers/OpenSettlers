package org.soc.gwt.client.game.actions;

import org.soc.common.game.actions.BuyDevelopmentCard.BuyDevelopmentCardView;
import org.soc.common.game.actions.WantsBuyDevelopmentCardEvent;
import org.soc.common.game.actions.WantsBuyDevelopmentCardEvent.WantsBuyDevelopmentCardHandler;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BuyDevelopmentCardGwt implements BuyDevelopmentCardView {
  AbsolutePanel rootPanel = new AbsolutePanel();
  VerticalPanel tradePanel = new VerticalPanel();
  Image trade1 = new Image(R.icons().trade16());
  Image trade2 = new Image(R.icons().trade16());
  Image trade3 = new Image(R.icons().trade16());
  PushButton btnbuyDvelopmentcard = new PushButton(new Image(R
          .icons().buyDvelopmentCard48()));
  EventBus eventBus = new SimpleEventBus();

  public BuyDevelopmentCardGwt() {
    super();
    tradePanel.add(trade1);
    tradePanel.add(trade2);
    tradePanel.add(trade3);
    rootPanel.setSize("4em", "4em");
    rootPanel.add(btnbuyDvelopmentcard);
    rootPanel.add(tradePanel, 3, 3);
    btnbuyDvelopmentcard.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsBuyDevelopmentCardEvent());
      }
    });
  }
  @Override public void enable() {
    btnbuyDvelopmentcard.setEnabled(true);
  }
  @Override public void disable() {
    btnbuyDvelopmentcard.setEnabled(false);
  }
  @Override public void setTrade1Visible(boolean visible) {
    trade1.setVisible(visible);
  }
  @Override public void setTrade2Visible(boolean visible) {
    trade2.setVisible(visible);
  }
  @Override public void setTrade3Visible(boolean visible) {
    trade3.setVisible(visible);
  }
  @Override public HandlerRegistration addWantsBuyDevelopmentCardHandler(
          WantsBuyDevelopmentCardHandler handler) {
    return eventBus.addHandler(WantsBuyDevelopmentCardEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
