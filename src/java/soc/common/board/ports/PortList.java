package soc.common.board.ports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.event.shared.GwtEvent.Type;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public class PortList implements Iterable<Port>
{
    private ResourceList tradeableResourceTypes = ResourceList.tradeableResources();
    private SimpleEventBus eventBus;
    private List<Port> ports = new ArrayList<Port>();
    
    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }
        
        return eventBus;
    }
    private void safelyFireEvent(PortListChangedEvent event)
    {
        if (eventBus !=null)
        {
            eventBus.fireEvent(event);
        }
    }
    
    /*
     * Returns amount of gold possibly be gained by given resources
     */
    public int amountGold(ResourceList resources)
    {
        // Have something to return
        int amountGold = 0;
        
        // Calculate amount of gold per possible resource type in which
        // we are allowed to trade.
        
        // For standard, this will be the 5 basic resources
        // For CitiesKnights, this will be standard + coin/paper/cloth
        for (Resource type : tradeableResourceTypes)
        {
            // get a list of resources of given type
            ResourceList resourcesOfType = resources.ofType(type);
            
            // Amount of gold we can get with current port
            int portGoldAmount = 0;
            
            // Return the highest amount of gold given a set of ports
            for (Port port : ports)
            {
                // Get amount of gold we can get with given resourcetype and port
                int possibleGold = port.divide(resourcesOfType, type);
                
                // When a port tops production of any previous port, set it 
                if (possibleGold > portGoldAmount)
                {
                    portGoldAmount = possibleGold;
                }
            }
            
            // Add the gold amount to the total amount of gold 
            amountGold += portGoldAmount;
        }
        
        // Tada! Here we have the maximum amount of gold we can trade for,
        // given: 
        // -a set of ports 
        // -a set of resources
        // -a set of resources allowed to trade with
        return amountGold;
    }
    
    public PortList()
    {
        // Add default 4:1 port to the list of ports
        ports.add(new FourToOnePort());
    }
    
    public void add(Port port)
    {
        ports.add(port);
        safelyFireEvent(new PortListChangedEvent(port, null));
    }
    
    public void remove(Port port)
    {
        ports.remove(port);
        safelyFireEvent(new PortListChangedEvent(null, port));
    }
    
    @Override
    public Iterator<Port> iterator()
    {
        return ports.iterator();
    }
    
    public void addPortListChangedEventHandler(PortListChangedEventHandler handler)
    {
        getEventBus().addHandler(PortListChangedEvent.TYPE, handler);
    }
}
