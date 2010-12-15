package soc.gwtClient.game.widgets.abstractWidgets;

import java.util.ArrayList;

import soc.common.board.ports.PortList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.gwtClient.game.widgets.standard.bitmap.BitmapResourceWidget;
import soc.message.BankTrade;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResourceListWidget implements IResourceListWidget
{
    protected ComplexPanel rootPanel;
    protected int maximumResources;
    protected ResourceList resources;
    protected ResourceList bankResources;
    protected PortList ports;
    
    public AbstractResourceListWidget(ResourceList resources, ResourceList bankResources, PortList ports)
    {
        this.ports=ports;
        this.bankResources=bankResources;
        rootPanel = createRootPanel();
        rootPanel.setHeight("100px");

        setResources(resources);
        addResources(resources);
    }
    /**
     * @return the resources
     */
    @Override
    public ResourceList getResources()
    {
        return resources;
    }
    
    /* (non-Javadoc)
     * @see soc.gwtClient.game.widgets.abstractWidgets.IResourceListWidget#setEnabled(boolean)
     */
    @Override
    public IResourceListWidget setEnabled(boolean enabled)
    {
        for (int i=0; i< rootPanel.getWidgetCount(); i++)
        {
            IResourceWidget resourceWidget = (IResourceWidget)rootPanel.getWidget(i);
            resourceWidget.setEnabled(enabled);
        }
        
        return this;
    }
    /**
     * @param resources the resources to set
     */
    @Override
    public IResourceListWidget setResources(ResourceList resources)
    {
        //TODO: Remove old event handler
        this.resources = resources;
    
        // Get rid of any old widgets
        rootPanel.clear();
        
        // Add new widgets if any
        for (Resource resource : resources)
        {
            IResourceWidget resourceWidget = createResourceWidget(resource);
            resourceWidget.addClickHandler(this);
            rootPanel.add(resourceWidget);
        }
        
        // Listen to changes on the new resource list
        resources.addResourcesChangedEventHandler(this);
        
        return this;
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
            IResourceWidget resourceWidget = createResourceWidget(resource);
            resourceWidget.addClickHandler(this);
            rootPanel.add(resourceWidget);
        }
    }

    private void removeResources(ResourceList resourcesToRemove)
    {
        ArrayList<IResourceWidget> widgetsToRemove = new ArrayList<IResourceWidget>();
        ResourceList resToRemove = resourcesToRemove.copy();
        
        for (Resource resourceToRemove : resToRemove)
        {
            for (int i=0; i< rootPanel.getWidgetCount(); i++)
            {
                Object o = rootPanel.getWidget(i);
                IResourceWidget resourceWidget = (IResourceWidget)rootPanel.getWidget(i);
                if (resourceWidget.getResource().getClass() == resourceToRemove.getClass())
                {
                    rootPanel.remove(i);
                    break;
                }
            }            
        }
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
    
    @Override
    public void onResourceClicked(ResourceClickedEvent event)
    {
        Resource removedResource = event.getResource();
        
        if (ports !=null)
        {
            ResourceList resourcesToRemove = new ResourceList();
            for (int i=0; i < ports.amountNeededToTrade(removedResource); i++)
            {
                resourcesToRemove.add(removedResource.Copy());
            }
            resources.remove(resourcesToRemove, false);
            if (bankResources !=null)
            {
                bankResources.add(resourcesToRemove);
            }
        }
        else
        {
            resources.remove(removedResource);
            if (bankResources !=null)
            {
                bankResources.add(removedResource);
            }
        }
    }
}
