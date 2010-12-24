package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.ports.PortList;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResourcePickerWidget implements IResourcePickerWidget
{
    protected HorizontalPanel rootPanel = new HorizontalPanel();
    protected ResourceList resources;
    protected ResourceList bankResources;
    protected PortList ports;
    protected IGamePanel gamePanel;
    
    public AbstractResourcePickerWidget(ResourceList resources, PortList ports, ResourceList bankResources, IGamePanel gamePanel)
    {
        this.resources = resources;
        this.gamePanel = gamePanel;
        this.bankResources = bankResources;
        this.ports=ports;
        
        for (Resource resource : gamePanel.getGame().getGameRules().getSupportedResources())
        {
            IResourceSelectorWidget resourceSelector = createResourceSelectorWidget(resource, bankResources, ports);
            resourceSelector.addClickHandler(this);
            rootPanel.add(resourceSelector);
        }
    }

    /* (non-Javadoc)
     * @see soc.gwtClient.game.widgets.abstractWidgets.IResourcePickerWidget#setEnabled(boolean)
     */
    @Override
    public IResourcePickerWidget setEnabled(boolean enabled)
    {
        for (int i=0; i<rootPanel.getWidgetCount(); i++)
        {
            IResourceSelectorWidget selectorWidget = (IResourceSelectorWidget)rootPanel.getWidget(i);
            selectorWidget.setEnabled(enabled);
        }
        
        return this;
    }

    /* (non-Javadoc)
     * @see soc.gwtClient.game.widgets.abstractWidgets.ResourceClickedEventHandler#onResourceClicked(soc.gwtClient.game.widgets.abstractWidgets.ResourceClickedEvent)
     */
    @Override
    public void onResourceClicked(ResourceClickedEvent event)
    {
        Resource resourceToAdd = event.getResource();
        
        if (ports != null)
        {
            ResourceList resourcesToAdd = new ResourceList();
            for (int amount=0; amount < ports.amountNeededToTrade(resourceToAdd); amount++)
            {
                resourcesToAdd.add(resourceToAdd.copy());
            }
            resources.add(resourcesToAdd);
            
            // Remove the resources from the bank
            if (bankResources != null)
            {
                bankResources.remove(resourcesToAdd, false);
            }
        }
        else
        {
            resources.add(resourceToAdd.copy());
            
            // Remove the resource from the bank
            if (bankResources !=null)
            {
                bankResources.remove(resourceToAdd);
            }
        }
    }

    /**
     * @return the ports
     */
    public PortList getPorts()
    {
        return ports;
    }

    /**
     * @param When set, adds amount of resources to the resource list based on the best
     * trade a player can make using his ports
     */
    public IResourcePickerWidget setPorts(PortList ports)
    {
        this.ports = ports;
    
        return this;
    }

    @Override
    public ResourceList getResources()
    {
        return resources;
    }

    @Override
    public IResourcePickerWidget setResources(ResourceList resources)
    {
        this.resources=resources;

        return null;
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
