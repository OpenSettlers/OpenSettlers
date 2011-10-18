package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.Ports.PortList;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.generic.*;

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
