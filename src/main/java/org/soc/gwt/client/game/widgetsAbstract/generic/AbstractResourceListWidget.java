package org.soc.gwt.client.game.widgetsAbstract.generic;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.Ports.PortList;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.*;

import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.*;

public abstract class AbstractResourceListWidget implements ResourceListWidget,
        ListAdded<Resource>, ListRemoved<Resource>
{
  protected ComplexPanel rootPanel;
  protected int maximumResources;
  protected MutableResourceList resources;
  protected MutableResourceList bankResources;
  protected PortList ports;
  protected HandlerRegistration resourcesRegistration;

  public AbstractResourceListWidget(ResourceList resources,
          MutableResourceList bankResources, PortList ports)
  {
    this.ports = ports;
    this.bankResources = bankResources;
    rootPanel = createRootPanel();
    rootPanel.setHeight("100px");
    setResources(resources);
    addResources(resources);
  }
  @Override public MutableResourceList getResources() {
    return resources;
  }
  @Override public ResourceListWidget setEnabled(boolean enabled) {
    for (int i = 0; i < rootPanel.getWidgetCount(); i++) {
      ResourceWidget resourceWidget = (ResourceWidget) rootPanel.getWidget(i);
      resourceWidget.setEnabled(enabled);
    }
    return this;
  }
  @Override public ResourceListWidget setResources(ResourceList newResources) {
    // Remove handler from old resourcelist
    if (resourcesRegistration != null) {
      resourcesRegistration.removeHandler();
    }
    resources = new MutableResourceListImpl(newResources);
    // Get rid of any old widgets
    rootPanel.clear();
    // Add new widgets if any
    for (Resource resource : this.resources) {
      ResourceWidget resourceWidget = createResourceWidget(resource);
      resourceWidget.addResourceClickedHandler(this);
      rootPanel.add(resourceWidget);
    }
    // Listen to changes on the new resource list
    resourcesRegistration = this.resources.addListAddedHandler(this);
    return this;
  }
  public ResourceListWidget setBankResources(ResourceList bankResources)
  {
    this.bankResources = new MutableResourceListImpl(bankResources);
    return this;
  }
  private void addResources(ImmutableList<Resource> items) {
    for (Resource resource : items) {
      ResourceWidget resourceWidget = createResourceWidget(resource);
      resourceWidget.addResourceClickedHandler(this);
      rootPanel.add(resourceWidget);
    }
  }
  private void removeResources(ImmutableList<Resource> items) {
    ImmutableList<Resource> resToRemove = items.copy();
    for (Resource resourceToRemove : resToRemove) {
      for (int i = 0; i < rootPanel.getWidgetCount(); i++) {
        Object o = rootPanel.getWidget(i);
        ResourceWidget resourceWidget = (ResourceWidget) rootPanel.getWidget(i);
        if (resourceWidget.getResource().getClass() == resourceToRemove.getClass()) {
          rootPanel.remove(i);
          break;
        }
      }
    }
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onResourceClicked(ResourceClickedEvent event) {
    Resource removedResource = event.getResource();
    if (ports != null) {
      MutableResourceList resourcesToRemove = new MutableResourceListImpl();
      for (int i = 0; i < ports.amountNeededToTrade(removedResource); i++)
        resourcesToRemove.add(removedResource.copy());
      resources.removeList(resourcesToRemove.toImmutable());
      if (bankResources != null)
        bankResources.addList(resourcesToRemove);
    } else {
      resources.remove(removedResource);
      if (bankResources != null)
        bankResources.add(removedResource);
    }
  }
  @Override public void listRemoved(ImmutableList<Resource> items) {
    addResources(items);
  }
  @Override public void listAdded(ImmutableList<Resource> items) {
    removeResources(items);
  }
}
