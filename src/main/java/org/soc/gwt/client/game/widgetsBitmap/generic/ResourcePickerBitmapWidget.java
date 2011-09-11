package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.PortList;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceSelectorWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.generic.AbstractResourcePickerWidget;

public class ResourcePickerBitmapWidget extends AbstractResourcePickerWidget
{
  public ResourcePickerBitmapWidget(ResourceList resources,
          PortList ports, ResourceList bankResources, GameWidget gameWidget)
  {
    super(resources, ports, bankResources, gameWidget);
  }
  @Override public ResourceSelectorWidget createResourceSelectorWidget(
          Resource resource, ResourceList bankResources, PortList ports)
  {
    return new ResourceSelectorBitmapWidget(resource, bankResources, ports);
  }
}
