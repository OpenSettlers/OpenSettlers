package soc.common.views.widgetsInterface.generic;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface ResourcePickerWidget extends IsWidget, ResourceClickedEventHandler
{
    public ResourceSelectorWidget createResourceSelectorWidget(
            Resource resource, ResourceList bankResources, PortList ports);
    
    public ResourcePickerWidget setResources(ResourceList resources);
    public ResourceList getResources();

    public PortList getPorts();
    public ResourcePickerWidget setPorts(PortList ports);
    
    public ResourcePickerWidget setEnabled(boolean enabled);

    /**
     * @param bankResources the bankResources to set
     */
    public ResourcePickerWidget setBankResources(ResourceList bankResources);

    /**
     * @return the bankResources
     */
    public ResourceList getBankResources();
}
