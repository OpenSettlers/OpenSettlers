package soc.gwtClient.game.widgetsBitmap.generic;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayerImpl;
import soc.gwtClient.game.widgetsAbstract.AbstractResourcePickerWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourcePickerWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourceSelectorWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ResourcePickerBitmapWidget extends AbstractResourcePickerWidget
{
    public ResourcePickerBitmapWidget(ResourceList resources,
             PortList ports, ResourceList bankResources, GameWidget gameWidget)
    {
        super(resources, ports, bankResources, gameWidget);
    }

    @Override
    public ResourceSelectorWidget createResourceSelectorWidget(
            Resource resource, ResourceList bankResources, PortList ports)
    {
        return new ResourceSelectorBitmapWidget(resource, bankResources, ports);
    }
}
