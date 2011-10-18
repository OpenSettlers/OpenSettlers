package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class CancelPickEvent extends GwtEvent<CancelPickEvent.CancelPickHandler> { 

  public interface HasCancelPickHandlers extends HasHandlers {
    HandlerRegistration addCancelPickHandler(CancelPickHandler handler);
  }

  public interface CancelPickHandler extends EventHandler {
    public void onCancelPick(CancelPickEvent event);
  }

  private static final Type<CancelPickHandler> TYPE = new Type<CancelPickHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resource cancelledResource) {
    source.fireEvent(new CancelPickEvent(cancelledResource));
  }

  public static Type<CancelPickHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resource cancelledResource;

  public CancelPickEvent(org.soc.common.game.Resource cancelledResource) {
    this.cancelledResource = cancelledResource;
  }

  protected CancelPickEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<CancelPickHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resource getCancelledResource() {
    return cancelledResource;
  }

  @Override
  protected void dispatch(CancelPickHandler handler) {
    handler.onCancelPick(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    CancelPickEvent other = (CancelPickEvent) obj;
    if (cancelledResource == null) {
      if (other.cancelledResource != null)
        return false;
    } else if (!cancelledResource.equals(other.cancelledResource))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (cancelledResource == null ? 1 : cancelledResource.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "CancelPickEvent["
                 + cancelledResource
    + "]";
  }
}
