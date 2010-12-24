package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEventHandler;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

/*
 * Displays a list of resources. When Ports is set, it adds amount of resources
 * to the list of resources equal to the minimum needed to trade with the bank for that resource.
 * 
 * If a bank is set, it displays amount of resources available in the bank
 */
public interface IResourceListWidget extends IsWidget, ResourcesChangedEventHandler, ResourceClickedEventHandler
{
    // Returns a new base panel where widgets are put into 
    public ComplexPanel createRootPanel();
    
    // Create a Resourcewidget using a resource type
    public IResourceWidget createResourceWidget(Resource resourceType);
    
    public IResourceListWidget setResources(ResourceList resources);
    public ResourceList getResources();
    
    public IResourceListWidget setEnabled(boolean enabled);
}
