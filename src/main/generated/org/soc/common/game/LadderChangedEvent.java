package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class LadderChangedEvent extends GwtEvent<LadderChangedEvent.LadderChangedHandler> { 

  public interface HasLadderChangedHandlers extends HasHandlers {
    HandlerRegistration addLadderChangedHandler(LadderChangedHandler handler);
  }

  public interface LadderChangedHandler extends EventHandler {
    public void onLadderChanged(LadderChangedEvent event);
  }

  private static final Type<LadderChangedHandler> TYPE = new Type<LadderChangedHandler>();

  public static void fire(HasHandlers source, boolean isLadder) {
    source.fireEvent(new LadderChangedEvent(isLadder));
  }

  public static Type<LadderChangedHandler> getType() {
    return TYPE;
  }

  boolean isLadder;

  public LadderChangedEvent(boolean isLadder) {
    this.isLadder = isLadder;
  }

  protected LadderChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<LadderChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public boolean isIsLadder() {
    return isLadder;
  }

  @Override
  protected void dispatch(LadderChangedHandler handler) {
    handler.onLadderChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    LadderChangedEvent other = (LadderChangedEvent) obj;
    if (isLadder != other.isLadder)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Boolean(isLadder).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "LadderChangedEvent["
                 + isLadder
    + "]";
  }
}
