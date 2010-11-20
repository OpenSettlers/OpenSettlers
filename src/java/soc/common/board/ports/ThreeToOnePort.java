package soc.common.board.ports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soc.common.board.resources.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ThreeToOnePort extends Port
{

    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#divide(soc.common.board.resources.ResourceList, soc.common.board.resources.Resource)
     */
    @Override
    public int divide(ResourceList resources, Resource type)
    {
        // TODO Auto-generated method stub
        return resources.size() / getInAmount();
    }

    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#getInAmount()
     */
    @Override
    public int getInAmount()
    {
        return 3;
    }

    /* (non-Javadoc)
     * @see soc.common.board.ports.Port#getOutAmount()
     */
    @Override
    public int getOutAmount()
    {
        // TODO Auto-generated method stub
        return 1;
    }
}
