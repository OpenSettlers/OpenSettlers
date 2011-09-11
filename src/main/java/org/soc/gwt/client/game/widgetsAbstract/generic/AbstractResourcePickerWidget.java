package org.soc.gwt.client.game.widgetsAbstract.generic;

import java.util.HashMap;

import org.soc.common.game.PortList;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent;
import org.soc.common.views.widgetsInterface.generic.ResourcePickerWidget;
import org.soc.common.views.widgetsInterface.generic.ResourceSelectorWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResourcePickerWidget implements
        ResourcePickerWidget
{
  protected HorizontalPanel rootPanel = new HorizontalPanel();
  protected ResourceList resources;
  protected ResourceList bankResources;
  protected PortList ports;
  protected GameWidget gameWidget;
  protected HashMap<Resource, ResourceSelectorWidget> resourceSlectorWidgets = new HashMap<Resource, ResourceSelectorWidget>();

  public AbstractResourcePickerWidget(ResourceList resources, PortList ports,
          ResourceList bankResources, GameWidget gameWidget)
  {
    this.resources = resources;
    this.gameWidget = gameWidget;
    this.bankResources = bankResources;
    this.ports = ports;
    for (Resource resource : gameWidget.game().rules()
            .supportedResources())
    {
      ResourceSelectorWidget resourceSelector = createResourceSelectorWidget(
              resource, bankResources, ports);
      resourceSlectorWidgets.put(resource, resourceSelector);
      resourceSelector.addResourceClickedHandler(this);
      rootPanel.add(resourceSelector);
    }
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgets.abstractWidgets.IResourcePickerWidget#setEnabled
   * (boolean) */
  @Override public ResourcePickerWidget setEnabled(boolean enabled)
  {
    for (ResourceSelectorWidget widget : resourceSlectorWidgets.values())
    {
      widget.setEnabled(enabled);
    }
    return this;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgets.abstractWidgets.ResourceClickedEventHandler
   * #onResourceClicked (org.soc.gwt.client.game.widgets.abstractWidgets.ResourceClickedEvent) */
  @Override public void onResourceClicked(ResourceClickedEvent event)
  {
    Resource resourceToAdd = event.getResource();
    if (ports != null)
    {
      ResourceList resourcesToAdd = new ResourceList();
      for (int amount = 0; amount < ports
              .amountNeededToTrade(resourceToAdd); amount++)
      {
        resourcesToAdd.add(resourceToAdd.copy());
      }
      resources.addList(resourcesToAdd);
      // Remove the resources from the bank
      if (bankResources != null)
      {
        bankResources.remove(resourcesToAdd, false);
      }
    }
    else
    {
      resources.add(resourceToAdd.copy());
      // Remove the resource from the bank
      if (bankResources != null)
      {
        bankResources.remove(resourceToAdd);
      }
    }
  }
  /** @return the ports */
  public PortList getPorts()
  {
    return ports;
  }
  /** @param When set, adds amount of resources to the resource list based on the best trade a player
   * can make using his ports */
  public ResourcePickerWidget setPorts(PortList ports)
  {
    this.ports = ports;
    for (ResourceSelectorWidget widget : resourceSlectorWidgets.values())
      widget.setPorts(ports);
    return this;
  }
  @Override public ResourceList getResources()
  {
    return resources;
  }
  @Override public ResourcePickerWidget setResources(ResourceList resources)
  {
    this.resources = resources;
    return this;
  }
  /** @return the bankResources */
  public ResourceList getBankResources()
  {
    return bankResources;
  }
  /** @param bankResources the bankResources to set */
  public ResourcePickerWidget setBankResources(ResourceList bankResources)
  {
    this.bankResources = bankResources;
    for (ResourceSelectorWidget widget : resourceSlectorWidgets.values())
      widget.setBankResources(bankResources);
    return this;
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
