package soc.common.board.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.server.random.Random;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class ResourceList implements Iterable<Resource>
{
    // Notification mechanism
    SimpleEventBus eventBus;

    // Encapsulated list of resources
    private final List<Resource> resources = new ArrayList<Resource>();

    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }
        return eventBus;
    }

    private void fireSafeEvent(ResourcesChangedEvent event)
    {
        if (eventBus != null)
        {
            eventBus.fireEvent(event);
        }
    }

    public void moveTo(ResourceList destination, ResourceList toMove)
    {
        remove(toMove, false);
        destination.add(toMove);
    }

    /*
     * Adds given ResourceList to this list of resources
     */
    public void add(ResourceList resourcesToAdd)
    {
        if (resourcesToAdd == this)
        {
            throw new AssertionError();
        }
        for (Resource resource : resourcesToAdd)
        {
            resources.add(resource);
        }
        fireSafeEvent(new ResourcesChangedEvent(resourcesToAdd, null));
    }

    /*
     * Adds given resource to this list of resources
     */
    public void add(Resource resource)
    {
        resources.add(resource);

        if (eventBus != null)
        {
            // Notify the list has been changed with only one resource
            ResourceList addedResource = new ResourceList();
            addedResource.add(resource);
            fireSafeEvent(new ResourcesChangedEvent(addedResource, null));
        }
    }

    /*
     * Removes given resource from this list of resources
     */
    public void remove(Resource resource)
    {
        resources.remove(resource);

        if (eventBus != null)
        {
            // Notify the list has been changed with only one resource
            ResourceList removedResource = new ResourceList();
            removedResource.add(resource);
            fireSafeEvent(new ResourcesChangedEvent(null, removedResource));
        }
    }

    /*
     * Removes given resources from this list. If checkIfPossible is set, this
     * list should contain all of the resources contained in the given list
     */
    public void remove(ResourceList resourcesToRemove, boolean checkIfPossible)
    {
        if (checkIfPossible)
        {
            // First check if this list contains all resources in given list
            if (hasAtLeast(resourcesToRemove))
            {
                removeAll(resourcesToRemove);
            }
            else
            {
                throw new RuntimeException(
                        "Wants to remove non-existing resources");
            }
        }
        else
        {
            removeAll(resourcesToRemove);
        }
    }

    /*
     * Removes given resources from the list. Given resource does not need to be
     * contained
     */
    private void removeAll(ResourceList resourcesToRemove)
    {
        for (Resource resource : resourcesToRemove)
        {
            resources.remove(resource);
        }

        fireSafeEvent(new ResourcesChangedEvent(null, resourcesToRemove));
    }

    /*
     * Returns a copy of this ResourceList
     */
    public ResourceList copy()
    {
        ResourceList result = new ResourceList();

        result.add(this);

        return result;
    }

    /*
     * Returns a new ResourceList with only resources equivalent to given type
     */
    public ResourceList ofType(Resource type)
    {
        ResourceList result = new ResourceList();

        // Iterate over all resources and add the ones equalling given type
        for (Resource res : resources)
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
        return ofType(new Timber()).size() >= toHave.ofType(new Timber())
                .size()
                && ofType(new Wheat()).size() >= toHave.ofType(new Wheat())
                        .size()
                && ofType(new Ore()).size() >= toHave.ofType(new Ore()).size()
                && ofType(new Clay()).size() >= toHave.ofType(new Clay())
                        .size()
                && ofType(new Sheep()).size() >= toHave.ofType(new Sheep())
                        .size()
                && ofType(new Diamond()).size() >= toHave.ofType(new Diamond())
                        .size()
                && ofType(new Gold()).size() >= toHave.ofType(new Gold())
                        .size();
    }

    /*
     * Swaps a given list of resources from a source to this list
     */
    public void swapResourcesFrom(ResourceList resourcesToSwap,
            ResourceList from)
    {
        // ResourceList where we take resources from, should be able to
        // provide the resources
        if (!from.hasAtLeast(resourcesToSwap))
        {
            throw new RuntimeException(
                    "Trying to swap resources which ar not contained by the list");
        }

        // add the resources to this list...
        this.add(resourcesToSwap);

        // ...and remove them at the "from source"
        from.remove(resourcesToSwap, true);
    }

    /*
     * Returns amount of items halfed and rounded down
     */
    public int halfCount()
    {
        int count = size();
        // Make number even
        if (count % 2 == 1)
            count--;
        return count / 2;
    }

    /*
     * Returns a list of resources needed
     */
    public ResourceList getNeededResources(ResourceList neededResources)
    {
        ResourceList result = new ResourceList();
        ResourceList copy = this.copy();

        result.add(neededResources);
        for (Resource resource : neededResources)
        {
            if (copy.contains(resource))
            {
                result.remove(resource);
                copy.remove(resource);
            }
        }

        return result;
    }

    /*
     * Checks each resource if contained in the list, if so removes it
     */
    public void subtractResources(ResourceList resourcesToSubtract)
    {
        for (Resource resource : resourcesToSubtract)
        {
            if (resources.contains(resource))
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

    /*
     * Removes all resources from this list
     */
    public void clear()
    {
        removeAll(this.copy());
    }

    @Override
    public Iterator<Resource> iterator()
    {
        return resources.iterator();
    }

    public int size()
    {
        return resources.size();
    }

    public boolean contains(Resource resource)
    {
        return resources.contains(resource);
    }

    public HandlerRegistration addResourcesChangedEventHandler(
            ResourcesChangedEventHandler handler)
    {
        return getEventBus().addHandler(ResourcesChangedEvent.TYPE, handler);
    }

    public Resource getRandom(Random random)
    {
        int randomResource = random.nextInt(resources.size(), false);
        return resources.get(randomResource - 1);
    }

    /*
     * Returns true if this list contains resources which are tradeable
     */
    public boolean hasTradeableResources()
    {
        for (Resource resource : resources)
        {
            if (resource.isTradeable())
            {
                return true;
            }
        }
        return false;
    }

    public ResourceList getTradeableResources()
    {
        ResourceList result = new ResourceList();

        for (Resource resource : resources)
        {
            if (resource.isTradeable())
            {
                result.add(resource);
            }
        }

        return result;
    }

    public ResourceList getNonTradeableResources()
    {
        ResourceList result = new ResourceList();

        for (Resource resource : resources)
        {
            if (!resource.isTradeable())
            {
                result.add(resource);
            }
        }

        return result;
    }

    public Resource get(int i)
    {
        return resources.get(i);
    }
}
