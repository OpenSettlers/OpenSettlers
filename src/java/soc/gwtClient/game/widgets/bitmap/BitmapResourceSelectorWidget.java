package soc.gwtClient.game.widgets.bitmap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.board.ports.Port;
import soc.common.board.ports.PortList;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceSelectorWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceClickedEvent;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceClickedEventHandler;

public class BitmapResourceSelectorWidget implements IResourceSelectorWidget, ClickHandler, ResourcesChangedEventHandler
{
    protected Resource resource;
    protected ResourceList bankResources;
    protected PortList ports;
    private VerticalPanel rootPanel = new VerticalPanel();
    private Label lblAmountResources = new Label();
    private PushButton btnResource;
    private Image imgPort;
    private SimpleEventBus eventBus = new SimpleEventBus();
    
    public BitmapResourceSelectorWidget(Resource resource, ResourceList bankResources, PortList ports)
    {
        super();
        this.resource = resource;
        this.ports=ports;
        
        btnResource = new PushButton(ImageLibrary.getIcon(resource, 48));
        btnResource.addClickHandler(this);

        setBankResources(bankResources);
        
        rootPanel.add(btnResource);
        rootPanel.add(lblAmountResources);

        // Add port when available
        if (ports !=null)
        {
            Port port = ports.getPort(resource, true); 
            if (port != null)
            {
                imgPort = new Image(ImageLibrary.getIcon(port, 48));
                rootPanel.add(imgPort);
            }
        }
        rootPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    }

    private String createPortImageLocation(Port port)
    {
        String location =  "icons/48/";
        if (port instanceof ThreeToOnePort)
        {
            location += "31Port.png";
        }
        if (port instanceof TwoToOneResourcePort)
        {
            TwoToOneResourcePort twoToOneResourcePort = (TwoToOneResourcePort)port;
            AbstractResource resource = twoToOneResourcePort.getResource();
            location += resource.getName() + "Port.png";
        }
        
        return location;
    }

    /* (non-Javadoc)
     * @see soc.gwtClient.game.widgets.abstractWidgets.IResourceSelectorWidget#setEnabled(boolean)
     */
    @Override
    public IResourceSelectorWidget setEnabled(boolean enabled)
    {
        btnResource.setEnabled(enabled);
        
        return this;
    }

    /**
     * @return the bankResources
     */
    public ResourceList getBankResources()
    {
        return bankResources;
    }

    /**
     * @param bankResources the bankResources to set
     */
    public IResourceSelectorWidget setBankResources(ResourceList bankResources)
    {
        //TODO: remove old handler when present
        this.bankResources = bankResources;
        
        if (bankResources != null)
        {
            // Add a handler to resources changed event
            bankResources.addResourcesChangedEventHandler(this);
            
            // update the amount of rsources label
            lblAmountResources.setText(Integer.toString(bankResources.ofType(resource).size()));
            
            // determine amount of resources needed to make a trade
            int neededAmount = ports == null ? 1 : ports.amountNeededToTrade(resource);
            
            // Enable/disable the button depending on ability to trade with resources left
            btnResource.setEnabled(bankResources.ofType(resource).size() >= neededAmount);
        }
        
        // Only show bankresources when they are available
        lblAmountResources.setVisible(bankResources != null);
    
        return this;
    }

    @Override
    public HandlerRegistration addClickHandler(ResourceClickedEventHandler handler)
    {
        return eventBus.addHandler(ResourceClickedEvent.TYPE, handler);
    }

    @Override
    public Resource getResource()
    {
        return resource;
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onClick(ClickEvent arg0)
    {
        eventBus.fireEvent(new ResourceClickedEvent(resource));
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        // Get new amount of resources
        int amount = bankResources.ofType(resource).size();
        
        // update the ui text
        lblAmountResources.setText(Integer.toString(amount));
        
        // update enabled state of the button
        int neededResourceAmount = 0;
        if (ports !=null)
        {
            neededResourceAmount = ports.amountNeededToTrade(resource);
        }
        else
        {
            neededResourceAmount = 1;
        }
        
        // Enable the button if we still have enough resources for it
        btnResource.setEnabled(amount >= neededResourceAmount);
    }
}