package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class PickedEvent extends GwtEvent<PickedEvent.PickedHandler> { 

  public interface HasPickedHandlers extends HasHandlers {
    HandlerRegistration addPickedHandler(PickedHandler handler);
  }

  public interface PickedHandler extends EventHandler {
    public void onPicked(PickedEvent event);
  }

  private static final Type<PickedHandler> TYPE = new Type<PickedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resource picked) {
    source.fireEvent(new PickedEvent(picked));
  }

  public static Type<PickedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resource picked;

  public PickedEvent(org.soc.common.game.Resource picked) {
    this.picked = picked;
  }

  protected PickedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<PickedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resource getPicked() {
    return picked;
  }

  @Override
  protected void dispatch(PickedHandler handler) {
    handler.onPicked(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PickedEvent other = (PickedEvent) obj;
    if (picked == null) {
      if (other.picked != null)
        return false;
    } else if (!picked.equals(other.picked))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (picked == null ? 1 : picked.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PickedEvent["
                 + picked
    + "]";
  }
}
