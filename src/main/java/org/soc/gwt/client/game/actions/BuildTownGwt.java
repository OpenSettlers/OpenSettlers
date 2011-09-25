package org.soc.gwt.client.game.actions;

import org.soc.common.game.actions.BuildTown.BuildTownView;
import org.soc.common.game.actions.WantsBuildTownEvent;
import org.soc.common.game.actions.WantsBuildTownEvent.WantsBuildTownHandler;
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

public class BuildTownGwt implements BuildTownView {
  private AbsolutePanel absolutePanel = new AbsolutePanel();
  private VerticalPanel tradesPanel1 = new VerticalPanel();
  private VerticalPanel tradesPanel2 = new VerticalPanel();
  private PushButton btnBuildTown = new PushButton(new Image(R.icons().town48()));
  private Image trade1 = new Image(R.icons().trade16());
  private Image trade2 = new Image(R.icons().trade16());
  private Image trade3 = new Image(R.icons().trade16());
  private Image trade4 = new Image(R.icons().trade16());
  private EventBus eventBus = new SimpleEventBus();

  public BuildTownGwt() {
    absolutePanel.setSize("60px", "60px");
    tradesPanel1.add(trade1);
    tradesPanel1.add(trade2);
    tradesPanel1.add(trade3);
    tradesPanel2.add(trade4);
    absolutePanel.add(btnBuildTown, 0, 0);
    absolutePanel.add(tradesPanel1, 3, 3);
    absolutePanel.add(tradesPanel2, 19, 3);
    btnBuildTown.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsBuildTownEvent());
      }
    });
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
  @Override public void enable() {
    btnBuildTown.setEnabled(true);
    setTradesVisible(true);
  }
  @Override public void disable() {
    btnBuildTown.setEnabled(false);
    setTradesVisible(false);
  }
  @Override public void setTradesVisible(boolean visible) {
    tradesPanel1.setVisible(visible);
    tradesPanel2.setVisible(visible);
  }
  @Override public HandlerRegistration addWantsBuildTownHandler(WantsBuildTownHandler handler) {
    return eventBus.addHandler(WantsBuildTownEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
