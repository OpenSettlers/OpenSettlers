package soc.gwtClient.game.widgets.bitmap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceClickedEvent;
import soc.gwtClient.game.widgets.standard.bitmap.ResourceBitmapWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResourceListBitmapWidget extends AbstractResourceListWidget
{
    public ResourceListBitmapWidget(ResourceList resources, ResourceList bankResources, PortList ports)
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
}
