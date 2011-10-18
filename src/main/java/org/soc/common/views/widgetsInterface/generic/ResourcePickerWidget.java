package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.game.Ports.PortList;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent.ResourceClickedHandler;

import com.google.gwt.user.client.ui.*;

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
