package org.soc.gwt.client.game.widgetsAbstract.generic;

import java.util.*;

import org.soc.common.game.Ports.PortList;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;

import com.google.gwt.user.client.ui.*;

public abstract class AbstractResourcePickerWidget implements
        ResourcePickerWidget
{
  protected HorizontalPanel rootPanel = new HorizontalPanel();
  protected MutableResourceList resources;
  protected MutableResourceList bankResources;
  protected PortList ports;
  protected GameWidget gameWidget;
  protected HashMap<Resource, ResourceSelectorWidget> resourceSlectorWidgets = new HashMap<Resource, ResourceSelectorWidget>();

  public AbstractResourcePickerWidget(ResourceList resources, PortList ports,
          ResourceList bankResources, GameWidget gameWidget)
  {
    this.resources = new MutableResourceListImpl(resources);
    this.gameWidget = gameWidget;
    this.bankResources = new MutableResourceListImpl(bankResources);
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
      MutableResourceList resourcesToAdd = new MutableResourceListImpl();
      for (int amount = 0; amount < ports
              .amountNeededToTrade(resourceToAdd); amount++)
      {
        resourcesToAdd.add(resourceToAdd.copy());
      }
      resources.addList(resourcesToAdd);
      // Remove the resources from the bank
      if (bankResources != null)
      {
        bankResources.removeList(resourcesToAdd);
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
    this.resources = new MutableResourceListImpl(resources);
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
    this.bankResources = new MutableResourceListImpl(bankResources);
    for (ResourceSelectorWidget widget : resourceSlectorWidgets.values())
      widget.setBankResources(bankResources);
    return this;
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
