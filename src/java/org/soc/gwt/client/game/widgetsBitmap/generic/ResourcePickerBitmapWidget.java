package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.board.ports.PortList;
import org.soc.common.board.resources.AbstractResource;
import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.game.player.GamePlayerImpl;
import org.soc.common.views.widgetsInterface.generic.ResourcePickerWidget;
import org.soc.common.views.widgetsInterface.generic.ResourceSelectorWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.generic.AbstractResourcePickerWidget;

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
