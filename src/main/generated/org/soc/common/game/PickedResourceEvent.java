package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class PickedResourceEvent extends GwtEvent<PickedResourceEvent.PickedResourceHandler> { 

  public interface HasPickedResourceHandlers extends HasHandlers {
    HandlerRegistration addPickedResourceHandler(PickedResourceHandler handler);
  }

  public interface PickedResourceHandler extends EventHandler {
    public void onPickedResource(PickedResourceEvent event);
  }

  private static final Type<PickedResourceHandler> TYPE = new Type<PickedResourceHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resource pickedResource, org.soc.common.game.Resource unpickedResource) {
    source.fireEvent(new PickedResourceEvent(pickedResource, unpickedResource));
  }

  public static Type<PickedResourceHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resource pickedResource;
  org.soc.common.game.Resource unpickedResource;

  public PickedResourceEvent(org.soc.common.game.Resource pickedResource, org.soc.common.game.Resource unpickedResource) {
    this.pickedResource = pickedResource;
    this.unpickedResource = unpickedResource;
  }

  protected PickedResourceEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<PickedResourceHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resource getPickedResource() {
    return pickedResource;
  }

  public org.soc.common.game.Resource getUnpickedResource() {
    return unpickedResource;
  }

  @Override
  protected void dispatch(PickedResourceHandler handler) {
    handler.onPickedResource(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PickedResourceEvent other = (PickedResourceEvent) obj;
    if (pickedResource == null) {
      if (other.pickedResource != null)
        return false;
    } else if (!pickedResource.equals(other.pickedResource))
      return false;
    if (unpickedResource == null) {
      if (other.unpickedResource != null)
        return false;
    } else if (!unpickedResource.equals(other.unpickedResource))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (pickedResource == null ? 1 : pickedResource.hashCode());
    hashCode = (hashCode * 37) + (unpickedResource == null ? 1 : unpickedResource.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PickedResourceEvent["
                 + pickedResource
                 + ","
                 + unpickedResource
    + "]";
  }
}
