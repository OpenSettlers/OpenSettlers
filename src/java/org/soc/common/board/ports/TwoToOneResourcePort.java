package org.soc.common.board.ports;

import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.ResourceList;

public abstract class TwoToOneResourcePort extends AbstractPort
{
    private static final long serialVersionUID = 2615564785346537011L;
    private Resource resource;

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.ports.Port#getInAmount()
     */
    @Override
    public int getInAmount()
    {
        return 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.ports.Port#getOutAmount()
     */
    @Override
    public int getOutAmount()
    {
        return 1;
    }

    /*
     * Returns amount of gold gained by given list of resources
     * 
     * @see
     * org.soc.common.board.ports.Port#divide(org.soc.common.board.resources.ResourceList
     * , org.soc.common.board.resources.Resource)
     */
    @Override
    public int divide(ResourceList resources, Resource type)
    {
        return resources.size() / getInAmount();
    }

    @Override
    public String getColor()
    {
        return getResource().getColor();
    }

    @Override
    public boolean hasResource()
    {
        return true;
    }
}
