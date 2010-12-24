package soc.gwtClient.game.widgets.bitmap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceClickedEvent;
import soc.gwtClient.game.widgets.standard.bitmap.ResourceBitmapWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BitmapResourceListWidget extends AbstractResourceListWidget
{
    public BitmapResourceListWidget(ResourceList resources, ResourceList bankResources, PortList ports)
    {
        super(resources, bankResources, ports);
    }

    @Override
    public IResourceWidget createResourceWidget(Resource resource)
    {
        return new ResourceBitmapWidget(resource);
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
}
