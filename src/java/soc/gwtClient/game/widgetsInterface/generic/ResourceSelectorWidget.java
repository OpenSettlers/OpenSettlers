package soc.gwtClient.game.widgetsInterface.generic;

import soc.common.board.ports.PortList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

/*
 * Widget representing a resource selector widget. Usually a button
 * which spawns an event when clicked
 */
public interface ResourceSelectorWidget extends IsWidget
{
    public Resource getResource();
    public HandlerRegistration addClickHandler(ResourceClickedEventHandler handler);
    public ResourceSelectorWidget setEnabled(boolean enabled);
    /**
     * @return the ports
     */
    public PortList getPorts();
    /**
     * @param ports
     *            the ports to set
     */
    public ResourceSelectorWidget setPorts(PortList ports);
    /**
     * @param bankResources
     *            the bankResources to set
     */
    public ResourceSelectorWidget setBankResources(ResourceList bankResources);
}
