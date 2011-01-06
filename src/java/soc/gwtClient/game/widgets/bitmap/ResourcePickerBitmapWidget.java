package soc.gwtClient.game.widgets.bitmap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayerImpl;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractResourcePickerWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcePickerWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceSelectorWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ResourcePickerBitmapWidget extends AbstractResourcePickerWidget
{
    public ResourcePickerBitmapWidget(ResourceList resources,
             PortList ports, ResourceList bankResources, GamePanel gamePanel)
    {
        super(resources, ports, bankResources, gamePanel);
    }

    @Override
    public ResourceSelectorWidget createResourceSelectorWidget(
            Resource resource, ResourceList bankResources, PortList ports)
    {
        return new ResourceSelectorBitmapWidget(resource, bankResources, ports);
    }
}
