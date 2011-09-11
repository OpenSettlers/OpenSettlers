package org.soc.common.board;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class PortListChangedEvent extends GwtEvent<PortListChangedEvent.PortListChangedHandler> { 

  public interface HasPortListChangedHandlers extends HasHandlers {
    HandlerRegistration addPortListChangedHandler(PortListChangedHandler handler);
  }

  public interface PortListChangedHandler extends EventHandler {
    public void onPortListChanged(PortListChangedEvent event);
  }

  private static final Type<PortListChangedHandler> TYPE = new Type<PortListChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Port addedPort, org.soc.common.game.Port removedPort) {
    source.fireEvent(new PortListChangedEvent(addedPort, removedPort));
  }

  public static Type<PortListChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Port addedPort;
  org.soc.common.game.Port removedPort;

  public PortListChangedEvent(org.soc.common.game.Port addedPort, org.soc.common.game.Port removedPort) {
    this.addedPort = addedPort;
    this.removedPort = removedPort;
  }

  protected PortListChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<PortListChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Port getAddedPort() {
    return addedPort;
  }

  public org.soc.common.game.Port getRemovedPort() {
    return removedPort;
  }

  @Override
  protected void dispatch(PortListChangedHandler handler) {
    handler.onPortListChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PortListChangedEvent other = (PortListChangedEvent) obj;
    if (addedPort == null) {
      if (other.addedPort != null)
        return false;
    } else if (!addedPort.equals(other.addedPort))
      return false;
    if (removedPort == null) {
      if (other.removedPort != null)
        return false;
    } else if (!removedPort.equals(other.removedPort))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (addedPort == null ? 1 : addedPort.hashCode());
    hashCode = (hashCode * 37) + (removedPort == null ? 1 : removedPort.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PortListChangedEvent["
                 + addedPort
                 + ","
                 + removedPort
    + "]";
  }
}
