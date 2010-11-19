package soc.common.board.ports;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;


public class PortList extends ArrayList<Port>
{
    public int possibleTradesCount(ResourceList resources)
    {
        int possibleTrades=0;
        for (Port port : this)
        {
            List<Resource> resourcesOfType = resources.ofType(resource);
            possibleTrades += resourcesOfType.size() / 3;
        }
        return possibleTrades;
    }
}
