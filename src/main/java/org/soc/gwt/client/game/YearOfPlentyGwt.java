package org.soc.gwt.client.game;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.game.DevelopmentCard.YearOfPlenty.YearOfPlentyView;
import org.soc.common.game.*;
import org.soc.common.game.PickedResourceEvent.PickedResourceHandler;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.*;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.gwt.client.game.widgetsBitmap.generic.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.*;

public class YearOfPlentyGwt implements YearOfPlentyView {
  private Button buttonnPlay = new Button(I.get().constants().play());
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private ResourceListWidget resourceListWidget;
  private ResourcePickerWidget resourcePickerWidget;
  private MutableResourceList pickedResources = new MutableResourceListImpl();
  private EventBus eventBus = new SimpleEventBus();

  public YearOfPlentyGwt(ResourceList bank) {
    super();
    resourceListWidget = new ResourceListBitmapWidget(pickedResources,
            new MutableResourceListImpl(bank.copy()), null);
    resourceListWidget.setHeight("3em");
    resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources,
            null, new MutableResourceListImpl(bank.copy()), null);
    rootPanel.setSpacing(5);
    rootPanel.add(new Image(R.icons.yearOfPlenty48()));
    rootPanel.add(new Label(I.get().constants().yearOfPlenty()));
    rootPanel.add(resourcePickerWidget);
    rootPanel.add(resourceListWidget);
    rootPanel.add(buttonnPlay);
    buttonnPlay.setEnabled(false);
    buttonnPlay.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        eventBus.fireEvent(new WantsPlayDevelopmentCardEvent());
      }
    });
    pickedResources.addRemovedHandler(new Removed<Resource>() {
      @Override public void removed(Resource item) {
        eventBus.fireEvent(new PickedResourceEvent(null, item));
      }
    });
    pickedResources.addAddedHandler(new Added<Resource>() {
      @Override public void added(Resource item) {
        eventBus.fireEvent(new PickedResourceEvent(item, null));
      }
    });
  }
  @Override public void enable() {
    buttonnPlay.setEnabled(true);
  }
  @Override public void disable() {
    buttonnPlay.setEnabled(true);
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
  @Override public ResourceList pickedResources() {
    return pickedResources;
  }
}
