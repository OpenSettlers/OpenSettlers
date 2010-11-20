package soc.common.board.ports;

import java.util.List;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public class TwoToOneResourcePort extends Port
{
    private Resource resource;

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


    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#getResource()
     */
    @Override
    public Resource getResource()
    {
        return resource;
    }


    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#divide(soc.common.board.resources.ResourceList, soc.common.board.resources.Resource)
     */
    @Override
    public int divide(ResourceList resources, Resource type)
    {
        // TODO Auto-generated method stub
        return resources.size() / getInAmount();
    }


}
