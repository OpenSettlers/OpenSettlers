package soc.common.board.ports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soc.common.board.resources.*;

public class ThreeToOnePort extends Port
{

    /* Performs a 3:1 trade on a list of resources
     * @see soc.common.board.ports.Port#divide(soc.common.board.resources.ResourceList, soc.common.board.resources.Resource)
     */
    @Override
    public int divide(ResourceList resources, Resource type)
    {
        // TODO Auto-generated method stub
        return resources.size() / getInAmount();
    }

    /* Three resources are needed for one trade
     * @see soc.common.board.ports.Port#getInAmount()
     */
    @Override
    public int getInAmount()
    {
        return 3;
    }

    /* One gold is gained from one trade
     * @see soc.common.board.ports.Port#getOutAmount()
     */
    @Override
    public int getOutAmount()
    {
        // TODO Auto-generated method stub
        return 1;
    }
}
