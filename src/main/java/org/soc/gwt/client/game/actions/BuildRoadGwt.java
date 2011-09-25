package org.soc.gwt.client.game.actions;

import org.soc.common.game.actions.BuildRoad.BuildRoadView;
import org.soc.common.game.actions.WantsBuildRoadEvent;
import org.soc.common.game.actions.WantsBuildRoadEvent.WantsBuildRoadHandler;
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

public class BuildRoadGwt implements BuildRoadView {
  AbsolutePanel absolutePanel = new AbsolutePanel();
  VerticalPanel tradesPanel = new VerticalPanel();
  VerticalPanel roadTokensPanel = new VerticalPanel();
  PushButton btnBuildRoad = new PushButton(new Image(R.icons().road48()));
  Image trade1 = new Image(R.icons().trade16());
  Image trade2 = new Image(R.icons().trade16());
  Image roadToken1 = new Image(R.icons().roadToken16());
  Image roadToken2 = new Image(R.icons().roadToken16());
  EventBus eventBus = new SimpleEventBus();

  public BuildRoadGwt() {
    super();
    absolutePanel.setSize("4em", "4em");
    btnBuildRoad.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsBuildRoadEvent());
      }
    });
    tradesPanel.add(trade1);
    tradesPanel.add(trade2);
    roadTokensPanel.add(roadToken1);
    roadTokensPanel.add(roadToken2);
    absolutePanel.add(btnBuildRoad, 0, 0);
    absolutePanel.add(tradesPanel, 3, 3);
    absolutePanel.add(roadTokensPanel, 3, 3);
  }
  @Override public void setRoadToken1Visible(boolean visbile) {
    roadToken1.setVisible(visbile);
  }
  @Override public void setRoadToken2Visible(boolean visbile) {
    roadToken2.setVisible(visbile);
  }
  @Override public void setTrade1Visible(boolean visible) {
    trade1.setVisible(visible);
  }
  @Override public void setTrade2Visible(boolean visible) {
    trade2.setVisible(visible);
  }
  @Override public void enable() {
    btnBuildRoad.setEnabled(true);
  }
  @Override public void disable() {
    btnBuildRoad.setEnabled(false);
  }
  @Override public HandlerRegistration addWantsBuildRoadHandler(WantsBuildRoadHandler handler) {
    return eventBus.addHandler(WantsBuildRoadEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
