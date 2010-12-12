package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResourceListWidget implements IResourceListWidget
{
    protected ComplexPanel rootPanel;
    protected int maximumResources;
    
    public AbstractResourceListWidget()
    {
    }    
    public AbstractResourceListWidget(ResourceList resources)
    {
        addResources(resources);
    }

    /* (non-Javadoc)
     * @see soc.common.board.resources.ResourcesChangedEventHandler#onResourcesChanged(soc.common.board.resources.ResourcesChangedEvent)
     */
    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (resourcesChanged.getAddedResources() !=null)
        {
            addResources(resourcesChanged.getAddedResources());
        }
        if (resourcesChanged.getRemovedResources() !=null)
        {
            removeResources(resourcesChanged.getRemovedResources());
        }
    }

    private void addResources(ResourceList resourcesToAdd)
    {
        for (Resource resource : resourcesToAdd)
        {
            rootPanel.add(createResourceWidget(resource));
        }
    }

    @Override
    public int getMaximumResources()
    {
        return maximumResources;
    }

    private void removeResources(ResourceList resourcesToRemove)
    {
        //TODO: remove old widgets from rootpanel
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
