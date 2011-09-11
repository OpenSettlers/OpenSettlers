package org.soc.common.board.hexes;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class PortChangedEvent extends GwtEvent<PortChangedEvent.PortChangedHandler> { 

  public interface HasPortChangedHandlers extends HasHandlers {
    HandlerRegistration addPortChangedHandler(PortChangedHandler handler);
  }

  public interface PortChangedHandler extends EventHandler {
    public void onPortChanged(PortChangedEvent event);
  }

  private static final Type<PortChangedHandler> TYPE = new Type<PortChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Port port) {
    source.fireEvent(new PortChangedEvent(port));
  }

  public static Type<PortChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Port port;

  public PortChangedEvent(org.soc.common.game.Port port) {
    this.port = port;
  }

  protected PortChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<PortChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Port getPort() {
    return port;
  }

  @Override
  protected void dispatch(PortChangedHandler handler) {
    handler.onPortChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PortChangedEvent other = (PortChangedEvent) obj;
    if (port == null) {
      if (other.port != null)
        return false;
    } else if (!port.equals(other.port))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (port == null ? 1 : port.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PortChangedEvent["
                 + port
    + "]";
  }
}
