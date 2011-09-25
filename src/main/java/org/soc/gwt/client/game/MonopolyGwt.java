package org.soc.gwt.client.game;

import org.soc.common.game.DevelopmentCard.Monopoly.MonopolyView;
import org.soc.common.game.PickedResourceEvent;
import org.soc.common.game.PickedResourceEvent.PickedResourceHandler;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler;
import org.soc.common.internationalization.I;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.generic.ResourcePickerWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourcePickerBitmapWidget;
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

public class MonopolyGwt implements MonopolyView {
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private Button btnPlay = new Button(I.get().constants().play());
  private ResourceListWidget resourceListWidget;
  private ResourcePickerWidget resourcePickerWidget;
  private ResourceList pickedResources = new ResourceList();
  private EventBus eventBus = new SimpleEventBus();

  public MonopolyGwt(ResourceList bank) {
    super();
    resourceListWidget = new ResourceListBitmapWidget(pickedResources, bank.copy(), null);
    resourceListWidget.setHeight("3em");
    resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources, null, bank.copy(), null);
    rootPanel.setSpacing(5);
    rootPanel.add(new Image(R.icons.monopoly48()));
    rootPanel.add(new Label(I.get().constants().monopoly()));
    rootPanel.add(resourcePickerWidget);
    rootPanel.add(resourceListWidget);
    rootPanel.add(btnPlay);
    btnPlay.setEnabled(false);
    btnPlay.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsPlayDevelopmentCardEvent());
      }
    });
    pickedResources.addResourcesChangedHandler(new ResourcesChangedHandler() {
      @Override public void onResourcesChanged(ResourcesChangedEvent event) {
        eventBus.fireEvent(new PickedResourceEvent(
                event.getAddedResources().get(0),
                event.getRemovedResources().get(0)));
      }
    });
  }
  @Override public void enable() {
    btnPlay.setEnabled(true);
  }
  @Override public void disable() {
    btnPlay.setEnabled(true);
  }
  @Override public HandlerRegistration addWantsPlayDevelopmentCardHandler(
          WantsPlayDevelopmentCardHandler handler) {
    return eventBus.addHandler(WantsPlayDevelopmentCardEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addPickedResourceHandler(PickedResourceHandler handler) {
    return eventBus.addHandler(PickedResourceEvent.getType(), handler);
  }
  @Override public Resource pickedResource() {
    return pickedResources.size() == 0 ? null : pickedResources.get(0);
  }
}
