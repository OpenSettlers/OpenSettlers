package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.game.PortList;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent.ResourceClickedHandler;

import com.google.gwt.user.client.ui.IsWidget;

public interface ResourcePickerWidget extends IsWidget, ResourceClickedHandler
{
  public ResourceSelectorWidget createResourceSelectorWidget(
          Resource resource, ResourceList bankResources, PortList ports);
  public ResourcePickerWidget setResources(ResourceList resources);
  public ResourceList getResources();
  public PortList getPorts();
  public ResourcePickerWidget setPorts(PortList ports);
  public ResourcePickerWidget setEnabled(boolean enabled);
  /** @param bankResources the bankResources to set */
  public ResourcePickerWidget setBankResources(ResourceList bankResources);
  /** @return the bankResources */
  public ResourceList getBankResources();
}
