package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class RoadTokensChangedEvent extends GwtEvent<RoadTokensChangedEvent.RoadTokensChangedHandler> { 

  public interface HasRoadTokensChangedHandlers extends HasHandlers {
    HandlerRegistration addRoadTokensChangedHandler(RoadTokensChangedHandler handler);
  }

  public interface RoadTokensChangedHandler extends EventHandler {
    public void onRoadTokensChanged(RoadTokensChangedEvent event);
  }

  private static final Type<RoadTokensChangedHandler> TYPE = new Type<RoadTokensChangedHandler>();

  public static void fire(HasHandlers source, int newTokenAmount) {
    source.fireEvent(new RoadTokensChangedEvent(newTokenAmount));
  }

  public static Type<RoadTokensChangedHandler> getType() {
    return TYPE;
  }

  int newTokenAmount;

  public RoadTokensChangedEvent(int newTokenAmount) {
    this.newTokenAmount = newTokenAmount;
  }

  protected RoadTokensChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<RoadTokensChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public int getNewTokenAmount() {
    return newTokenAmount;
  }

  @Override
  protected void dispatch(RoadTokensChangedHandler handler) {
    handler.onRoadTokensChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    RoadTokensChangedEvent other = (RoadTokensChangedEvent) obj;
    if (newTokenAmount != other.newTokenAmount)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Integer(newTokenAmount).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "RoadTokensChangedEvent["
                 + newTokenAmount
    + "]";
  }
}
