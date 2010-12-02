package soc.common.board.ports;

import java.util.ArrayList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;


public class PortList extends ArrayList<Port>
{
    private ResourceList tradeableResourceTypes = ResourceList.tradeableResources();
    
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
            for (Port port : this)
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
        this.add(new FourToOnePort());
    }
}
