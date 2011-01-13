package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

/*
 * Displays a list of resources. When Ports is set, it adds amount of resources
 * to the list of resources equal to the minimum needed to trade with the bank for that resource.
 * 
 * If a bank is set, it displays amount of resources available in the bank
 */
public interface ResourceListWidget extends IsWidget,
        ResourcesChangedEventHandler, ResourceClickedEventHandler
{
    // Returns a new base panel where widgets are put into
    public ComplexPanel createRootPanel();

    // Create a resource widget using given resource type
    public ResourceWidget createResourceWidget(Resource resourceType);

    public ResourceListWidget setResources(ResourceList resources);

    public ResourceList getResources();

    public ResourceListWidget setEnabled(boolean enabled);

    public void setHeight(String height);

    public ResourceListWidget setBankResources(ResourceList bankResources);
}
