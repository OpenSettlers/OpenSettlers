package soc.common.board.ports;

import java.util.List;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public class TwoToOneResourcePort extends Port
{
    private Resource resource;

    @Override
    public int possibleTradesCount(ResourceList resources)
    {
        int possibleTrades=0;
        for (Resource resource : tradeTypes)
        {
            List<Resource> resourcesOfType = resources.ofType(resource);
            possibleTrades += resourcesOfType.size() / 3;
        }
        return possibleTrades;
    }
    
    
    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#getDivider()
     */
    @Override
    public double getDivider()
    {
        return 2;
    }


    /**
     * @return the resource
     */
    public Resource getResource()
    {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public TwoToOneResourcePort setResource(Resource resource)
    {
        this.resource = resource;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
}
