package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.game.PortList;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent.HasResourceClickedHandlers;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Widget representing a resource selector widget. Usually a button
 * which spawns an event when clicked
 */
public interface ResourceSelectorWidget extends IsWidget, HasResourceClickedHandlers
{
  public Resource getResource();
  public ResourceSelectorWidget setEnabled(boolean enabled);
  /** @return the ports */
  public PortList getPorts();
  /** @param ports the ports to set */
  public ResourceSelectorWidget setPorts(PortList ports);
  /** @param bankResources the bankResources to set */
  public ResourceSelectorWidget setBankResources(ResourceList bankResources);
}
