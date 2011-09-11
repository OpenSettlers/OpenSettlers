package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ResourcesChangedEvent extends GwtEvent<ResourcesChangedEvent.ResourcesChangedHandler> { 

  public interface HasResourcesChangedHandlers extends HasHandlers {
    HandlerRegistration addResourcesChangedHandler(ResourcesChangedHandler handler);
  }

  public interface ResourcesChangedHandler extends EventHandler {
    public void onResourcesChanged(ResourcesChangedEvent event);
  }

  private static final Type<ResourcesChangedHandler> TYPE = new Type<ResourcesChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.ResourceList addedResources, org.soc.common.game.ResourceList removedResources) {
    source.fireEvent(new ResourcesChangedEvent(addedResources, removedResources));
  }

  public static Type<ResourcesChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.ResourceList addedResources;
  org.soc.common.game.ResourceList removedResources;

  public ResourcesChangedEvent(org.soc.common.game.ResourceList addedResources, org.soc.common.game.ResourceList removedResources) {
    this.addedResources = addedResources;
    this.removedResources = removedResources;
  }

  protected ResourcesChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ResourcesChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.ResourceList getAddedResources() {
    return addedResources;
  }

  public org.soc.common.game.ResourceList getRemovedResources() {
    return removedResources;
  }

  @Override
  protected void dispatch(ResourcesChangedHandler handler) {
    handler.onResourcesChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ResourcesChangedEvent other = (ResourcesChangedEvent) obj;
    if (addedResources == null) {
      if (other.addedResources != null)
        return false;
    } else if (!addedResources.equals(other.addedResources))
      return false;
    if (removedResources == null) {
      if (other.removedResources != null)
        return false;
    } else if (!removedResources.equals(other.removedResources))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (addedResources == null ? 1 : addedResources.hashCode());
    hashCode = (hashCode * 37) + (removedResources == null ? 1 : removedResources.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ResourcesChangedEvent["
                 + addedResources
                 + ","
                 + removedResources
    + "]";
  }
}
