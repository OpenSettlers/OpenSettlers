package soc.common.board.resources;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.hexes.Hex;
import soc.common.board.pieces.*;

public class ResourceList extends ArrayList<Resource>
{
    /*
     * Returns a copy of this ResourceList
     */
    public ResourceList copy()
    {
        ResourceList result = new ResourceList();
        
        result.addAll(this);
        
        return result;
    }
    
    /*
     * Returns a new ResourceList with only resources equivalent to given type
     */
    public ResourceList ofType(Resource type)
    {
        ResourceList result = new ResourceList();
        
        // Iterate over all resources and add the ones equalling given type
        for (Resource res : this)
        {
            if (res.getClass() == type.getClass())
            {
                result.add(res);                                                
            }
        }
        
        return result;
    }
    
    /*
     * Returns true if given resources are available in this ResourceList
     */
    public boolean hasAtLeast(ResourceList toHave)
    {
        return
            ofType(new Timber()).size() >= toHave.ofType(new Timber()).size() &&
            ofType(new Wheat()).size() >= toHave.ofType(new Wheat()).size()   &&
            ofType(new Ore()).size() >= toHave.ofType(new Ore()).size()       &&
            ofType(new Clay()).size() >= toHave.ofType(new Clay()).size()     &&
            ofType(new Sheep()).size() >= toHave.ofType(new Sheep()).size();
    }
    
    /*
     * Swaps a given list of resources from a source to this list
     */
    public void swapResourcesFrom(ResourceList resourcesToAdd, ResourceList from)
    {
        // ResourceList where we take resources from, should be able to 
        // provide the resources
        if (!from.hasAtLeast(resourcesToAdd))
        {
            throw new RuntimeException("Trying to swap resources which ar not contained by the list");
        }
        
        // add the resources to this list...
        this.addAll(resourcesToAdd);
        
        // ...and remove them at the "from source"
        from.removeAll(resourcesToAdd);
    }
    
    /*
     * Returns amount of items halfed and rounded down
     */
    public int halfCount()
    {
        int count = size();
        // Make number even
        if (count % 2 == 1) count--;
        return count / 2;
    }
    
    /*
     * Checks each resource if contained in the list, if so removes it
     */
    public void subtractResources(ResourceList resourcesToSubtract)
    {
        for (Resource resource : resourcesToSubtract)
        {
            if (this.contains(resource))
                this.remove(resource);
        }
    }
    
    public static ResourceList tradeableResources()
    {
        ResourceList result = new ResourceList();
        
        result.add(new Timber());
        result.add(new Wheat());
        result.add(new Ore());
        result.add(new Clay());
        result.add(new Sheep());
        
        return result;
    }

}
