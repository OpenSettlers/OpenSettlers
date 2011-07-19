package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.board.ports.PortList;
import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceWidget;
import org.soc.gwt.client.game.widgetsAbstract.generic.AbstractResourceListWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ResourceListBitmapWidget extends AbstractResourceListWidget
{
    public ResourceListBitmapWidget(ResourceList resources,
            ResourceList bankResources, PortList ports)
    {
        super(resources, bankResources, ports);
    }

    @Override
    public ResourceWidget createResourceWidget(Resource resource)
    {
        return new ResourceBitmapWidget(resource);
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public void setHeight(String height)
    {
        rootPanel.setHeight(height);
    }
}
