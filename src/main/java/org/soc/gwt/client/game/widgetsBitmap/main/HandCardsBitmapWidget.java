package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.Resource;
import org.soc.common.views.widgetsInterface.generic.ResourceWidget;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractHandCardsWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HandCardsBitmapWidget extends AbstractHandCardsWidget
{
  public HandCardsBitmapWidget(GamePlayer player)
  {
    super(player);
    for (Resource resource : player.resources())
    {
      addWidget(resource);
    }
    rootPanel.setWidth("5em");
    player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        for (Resource resource : items) {
          for (int i = 0; i < rootPanel.getWidgetCount(); i++) {
            ResourceWidget widget = (ResourceWidget) rootPanel.getWidget(i);
            if (widget.getResource().equals(resource)) {
              rootPanel.remove(widget);
              break;
            }
          }
        }
      }
    });
    player.resources().addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        for (Resource resource : items) {
          addWidget(resource);
        }
      }
    });
  }
  private void addWidget(Resource resource)
  {
    ResourceWidget widget = new ResourceBitmapWidget(resource);
    rootPanel.add(widget);
    resourcesWidgets.put(resource, widget);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override protected ComplexPanel createRootPanel()
  {
    return new VerticalPanel();
  }
}
