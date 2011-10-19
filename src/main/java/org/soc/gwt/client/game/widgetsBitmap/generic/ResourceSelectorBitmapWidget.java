package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.Ports.PortList;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent.ResourceClickedHandler;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.*;

public class ResourceSelectorBitmapWidget implements ResourceSelectorWidget,
        ClickHandler {
  private Resource resource;
  private ResourceList bankResources;
  private PortList ports;
  private VerticalPanel rootPanel = new VerticalPanel();
  private Label lblAmountResources = new Label();
  private PushButton btnResource;
  private Image imgPort;
  private SimpleEventBus eventBus = new SimpleEventBus();
  private HandlerRegistration bankResourcesHandlerRegistration;

  public ResourceSelectorBitmapWidget(Resource resource,
          ResourceList bankResources, PortList ports)
  {
    super();
    this.resource = resource;
    this.ports = ports;
    btnResource = new PushButton(new Image(Models.icon(resource)
            .iconDefault()));
    btnResource.addClickHandler(this);
    imgPort = new Image();
    setBankResources(bankResources);
    setPorts(ports);
    rootPanel.add(btnResource);
    rootPanel.add(lblAmountResources);
    rootPanel.add(imgPort);
    rootPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
  }
  @Override public ResourceSelectorWidget setEnabled(boolean enabled) {
    btnResource.setEnabled(enabled);
    return this;
  }
  public PortList getPorts() {
    return ports;
  }
  public ResourceSelectorWidget setPorts(PortList ports) {
    this.ports = ports;
    // Add port when available
    if (ports != null) {
      Port port = ports.bestPortForResource(resource, true);
      if (port != null) {
        imgPort.setUrl(Models.icon(port).iconDefault().getURL());
      }
    }
    return this;
  }
  public ResourceList getBankResources() {
    return bankResources;
  }
  public ResourceSelectorWidget setBankResources(ResourceList bankResources) {
    this.bankResources = bankResources;
    if (bankResourcesHandlerRegistration != null)
      bankResourcesHandlerRegistration.removeHandler();
    if (bankResources != null)
    {
      // Add a handler to resources changed event
      //      bankResourcesHandlerRegistration = bankResources
      //              .addResourcesChangedHandler(this);
      // update the amount of resources label
      lblAmountResources.setText(Integer.toString(bankResources.ofType(
              resource).size()));
      // determine amount of resources needed to make a trade
      int neededAmount = ports == null ? 1 : ports
              .amountNeededToTrade(resource);
      // Enable/disable the button depending on ability to trade with
      // resources left
      btnResource.setEnabled(bankResources.ofType(resource).size() >= neededAmount);
    }
    // Only show bank resources when they are available
    lblAmountResources.setVisible(bankResources != null);
    return this;
  }
  @Override public HandlerRegistration addResourceClickedHandler(
          ResourceClickedHandler handler)
  {
    return eventBus.addHandler(ResourceClickedEvent.getType(), handler);
  }
  @Override public Resource getResource() {
    return resource;
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
  @Override public void onClick(ClickEvent arg0) {
    eventBus.fireEvent(new ResourceClickedEvent(resource));
  }
  //  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged) {
  //    // Get new amount of resources
  //    int amount = bankResources.ofType(resource).size();
  //    // update the ui text
  //    lblAmountResources.setText(Integer.toString(amount));
  //    // update enabled state of the button
  //    int neededResourceAmount = 0;
  //    if (ports != null)
  //      neededResourceAmount = ports.amountNeededToTrade(resource);
  //    else
  //      neededResourceAmount = 1;
  //    // Enable the button if we still have enough resources for it
  //    btnResource.setEnabled(amount >= neededResourceAmount);
  //  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}