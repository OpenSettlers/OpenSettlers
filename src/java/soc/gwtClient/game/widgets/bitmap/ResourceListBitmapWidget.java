package soc.gwtClient.game.widgets.bitmap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceWidget;
import soc.gwtClient.game.widgets.standard.bitmap.ResourceBitmapWidget;

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
