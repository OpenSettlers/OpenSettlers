package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.Ports.PortList;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.gwt.client.game.widgetsAbstract.generic.*;

import com.google.gwt.user.client.ui.*;

public class ResourceListBitmapWidget extends AbstractResourceListWidget
{
  public ResourceListBitmapWidget(MutableResourceList resources,
          MutableResourceList bankResources, PortList ports)
  {
    super(resources, bankResources, ports);
  }
  @Override public ResourceWidget createResourceWidget(Resource resource)
  {
    return new ResourceBitmapWidget(resource);
  }
  @Override public ComplexPanel createRootPanel()
  {
    return new HorizontalPanel();
  }
  @Override public ResourceListBitmapWidget setHeight(String height)
  {
    rootPanel.setHeight(height);
    return this;
  }
}
