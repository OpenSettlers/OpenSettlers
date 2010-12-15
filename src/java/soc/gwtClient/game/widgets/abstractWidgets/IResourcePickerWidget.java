package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.ports.PortList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface IResourcePickerWidget extends IsWidget, ResourceClickedEventHandler
{
    public IResourceSelectorWidget createResourceSelectorWidget(
            Resource resource, ResourceList bankResources, PortList ports);
    
    public IResourcePickerWidget setResources(ResourceList resources);
    public ResourceList getResources();

    public PortList getPorts();
    public IResourcePickerWidget setPorts(PortList ports);
    
    public IResourcePickerWidget setEnabled(boolean enabled);
}
