package soc.common.board.ports;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public class TwoToOneResourcePort extends Port
{
    private Resource resource;
    
    public TwoToOneResourcePort(Resource r)
    {
        resource=r;
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


    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#getInAmount()
     */
    @Override
    public int getInAmount()
    {
        return 2;
    }


    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#getOutAmount()
     */
    @Override
    public int getOutAmount()
    {
        return 1;
    }


    /* Resource this port trades against
     * @see soc.common.board.ports.Port#getResource()
     */
    @Override
    public Resource getResource()
    {
        return resource;
    }


    /* Returns amount of gold gained by given list of resources
     * @see soc.common.board.ports.Port#divide(soc.common.board.resources.ResourceList, soc.common.board.resources.Resource)
     */
    @Override
    public int divide(ResourceList resources, Resource type)
    {
        return resources.size() / getInAmount();
    }

    /* Returns true when given resource type quals resource type of this port
     * @see soc.common.board.ports.Port#canTrade(soc.common.board.resources.Resource)
     */
    @Override
    public boolean canTrade(Resource resource)
    {
        return resource.getClass() == this.resource.getClass();
    }


}
