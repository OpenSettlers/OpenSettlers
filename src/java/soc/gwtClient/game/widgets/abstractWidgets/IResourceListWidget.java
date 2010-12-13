package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IResourceListWidget extends IsWidget, ResourcesChangedEventHandler
{
    public ComplexPanel createRootPanel();
    public int getMaximumResources();
    public IResourceWidget createResourceWidget(Resource resource);
    
    public IResourceListWidget setResources(ResourceList resources);
    public ResourceList getResources();
    
    public boolean isRemovePortAmount();
    public IResourceListWidget setRemovePortAmount(boolean removePortAmount);
}
