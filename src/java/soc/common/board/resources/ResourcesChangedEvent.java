package soc.common.board.resources;

import com.google.gwt.event.shared.GwtEvent;

public class ResourcesChangedEvent extends GwtEvent<ResourcesChangedEventHandler>
{
    public static Type<ResourcesChangedEventHandler> TYPE = new Type<ResourcesChangedEventHandler>();
    private ResourceList addedResources;
    private ResourceList removedResources;

    public ResourcesChangedEvent(ResourceList addedResources, ResourceList removedResources)
    {
        this.addedResources=addedResources;
        this.removedResources=removedResources;
    }    
    
    /**
     * @return the addedResources
     */
    public ResourceList getAddedResources()
    {
        return addedResources;
    }
    /**
     * @return the removedResources
     */
    public ResourceList getRemovedResources()
    {
        return removedResources;
    }
    
    /*
     * Returns the added or removed resources depending on which is not-null.
     */
    public ResourceList getChangedResources()
    {
        return addedResources != null ? addedResources : removedResources;
    }
    @Override
    protected void dispatch(ResourcesChangedEventHandler handler)
    {
        handler.onResourcesChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ResourcesChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
