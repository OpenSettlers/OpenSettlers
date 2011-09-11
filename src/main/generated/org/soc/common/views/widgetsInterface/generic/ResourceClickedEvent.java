package org.soc.common.views.widgetsInterface.generic;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ResourceClickedEvent extends GwtEvent<ResourceClickedEvent.ResourceClickedHandler> { 

  public interface HasResourceClickedHandlers extends HasHandlers {
    HandlerRegistration addResourceClickedHandler(ResourceClickedHandler handler);
  }

  public interface ResourceClickedHandler extends EventHandler {
    public void onResourceClicked(ResourceClickedEvent event);
  }

  private static final Type<ResourceClickedHandler> TYPE = new Type<ResourceClickedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resource resource) {
    source.fireEvent(new ResourceClickedEvent(resource));
  }

  public static Type<ResourceClickedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resource resource;

  public ResourceClickedEvent(org.soc.common.game.Resource resource) {
    this.resource = resource;
  }

  protected ResourceClickedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ResourceClickedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resource getResource() {
    return resource;
  }

  @Override
  protected void dispatch(ResourceClickedHandler handler) {
    handler.onResourceClicked(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ResourceClickedEvent other = (ResourceClickedEvent) obj;
    if (resource == null) {
      if (other.resource != null)
        return false;
    } else if (!resource.equals(other.resource))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (resource == null ? 1 : resource.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ResourceClickedEvent["
                 + resource
    + "]";
  }
}
