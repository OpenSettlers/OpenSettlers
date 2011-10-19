package org.soc.gwt.client.game.actions;

import org.soc.common.game.actions.BuildCity.BuidCityView;
import org.soc.common.game.actions.*;
import org.soc.common.game.actions.WantsBuildCityEvent.WantsBuildCityHandler;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.*;

public class BuildCityGwt implements BuidCityView {
  private AbsolutePanel absolutePanel = new AbsolutePanel();
  private VerticalPanel tradesPanel1 = new VerticalPanel();
  private VerticalPanel tradesPanel2 = new VerticalPanel();
  private PushButton btnCity = new PushButton(new Image(R.icons().city48()));
  private Image trade1 = new Image(R.icons().trade16());
  private Image trade2 = new Image(R.icons().trade16());
  private Image trade3 = new Image(R.icons().trade16());
  private Image trade4 = new Image(R.icons().trade16());
  private Image trade5 = new Image(R.icons().trade16());
  private EventBus eventBus = new SimpleEventBus();

  @Inject public BuildCityGwt(final EventBus eventBus) {
    this.eventBus = eventBus;
    absolutePanel.setSize("60px", "60px");
    tradesPanel1.add(trade1);
    tradesPanel1.add(trade2);
    tradesPanel1.add(trade3);
    tradesPanel2.add(trade4);
    tradesPanel2.add(trade5);
    absolutePanel.add(btnCity, 0, 0);
    absolutePanel.add(tradesPanel1, 3, 3);
    absolutePanel.add(tradesPanel2, 19, 3);
    btnCity.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsBuildCityEvent());
      }
    });
  }
  @Override public HandlerRegistration addWantsBuildCityHandler(WantsBuildCityHandler handler) {
    return eventBus.addHandler(WantsBuildCityEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
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
  @Override public void setTrade4Visible(boolean visible) {
    trade4.setVisible(visible);
  }
  @Override public void setTrade5Visible(boolean visible) {
    trade5.setVisible(visible);
  }
  @Override public void enable() {
    btnCity.setEnabled(true);
    setTradesVisible(true);
  }
  @Override public void disable() {
    btnCity.setEnabled(false);
    setTradesVisible(false);
  }
  @Override public void setTradesVisible(boolean visible) {
    tradesPanel1.setVisible(visible);
    tradesPanel2.setVisible(visible);
  }
}