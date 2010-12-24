package soc.gwtClient.game.widgets.bitmap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractResourcePickerWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourcePickerWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceSelectorWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BitmapResourcePickerWidget extends AbstractResourcePickerWidget
{
    public BitmapResourcePickerWidget(ResourceList resources,
             PortList ports, ResourceList bankResources, IGamePanel gamePanel)
    {
        super(resources, ports, bankResources, gamePanel);
    }

    @Override
    public IResourceSelectorWidget createResourceSelectorWidget(
            Resource resource, ResourceList bankResources, PortList ports)
    {
        return new BitmapResourceSelectorWidget(resource, bankResources, ports);
    }
}
