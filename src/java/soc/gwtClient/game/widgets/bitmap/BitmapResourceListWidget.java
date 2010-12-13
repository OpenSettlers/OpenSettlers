package soc.gwtClient.game.widgets.bitmap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceWidget;
import soc.gwtClient.game.widgets.standard.bitmap.BitmapResourceWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BitmapResourceListWidget implements IResourceListWidget, ClickHandler
{
    HorizontalPanel rootPanel;
    ResourceList resources;
    PortList ports;
    boolean removePortAmount;
    
    public BitmapResourceListWidget(ResourceList resources)
    {
        super();
        
        createRootPanel();
        setResources(resources);
    }

    /**
     * @return the removePortAmount
     */
    @Override
    public boolean isRemovePortAmount()
    {
        return removePortAmount;
    }

    /**
     * @param removePortAmount the removePortAmount to set
     */
    @Override
    public IResourceListWidget setRemovePortAmount(boolean removePortAmount)
    {
        this.removePortAmount = removePortAmount;
    
        return this;
    }

    /**
     * @return the resources
     */
    @Override
    public ResourceList getResources()
    {
        return resources;
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

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (resourcesChanged.getAddedResources() !=null)
        {
            for (Resource resource : resourcesChanged.getAddedResources())
            {
                rootPanel.add(new BitmapResourceWidget(resource));
            }
        }
        if (resourcesChanged.getRemovedResources() !=null)
        {
            //TODO:remove resources from panel
        }
    }

    @Override
    public IResourceWidget createResourceWidget(Resource resource)
    {
        return new BitmapResourceWidget(resource);
    }

    @Override
    public int getMaximumResources()
    {
        return 0;
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public void onClick(ClickEvent arg0)
    {
        Resource removedResource = ((IResourceWidget)arg0.getSource()).getResource();
        
        if (removePortAmount)
        {
            ResourceList resourcesToRemove = new ResourceList();
            for (int i=0; i < ports.amountNeededToTrade(removedResource); i++)
            {
                resourcesToRemove.add(removedResource.Copy());
            }
            resources.remove(resourcesToRemove, false);
        }
        else
        {
            resources.remove(removedResource);
        }
    }

}
