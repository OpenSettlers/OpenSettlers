package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent.ResourceClickedHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/*
 * Displays a list of resources. When Ports is set, it adds amount of resources
 * to the list of resources equal to the minimum needed to trade with the bank for that resource.
 * 
 * If a bank is set, it displays amount of resources available in the bank
 */
public interface ResourceListWidget extends IsWidget,
        ResourcesChangedHandler, ResourceClickedHandler
{
  // Returns a new base panel where widgets are put into
  public ComplexPanel createRootPanel();
  // Create a resource widget using given resource type
  public ResourceWidget createResourceWidget(Resource resourceType);
  public ResourceListWidget setResources(ResourceList resources);
  public ResourceList getResources();
  public ResourceListWidget setEnabled(boolean enabled);
  public void setHeight(String height);
  public ResourceListWidget setBankResources(ResourceList bankResources);

  @GenEvent public class ResourceClicked {
    @Order(0) Resource resource;
  }
}
