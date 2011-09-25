package org.soc.common.presenters;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class SayEvent extends GwtEvent<SayEvent.SayHandler> { 

  public interface HasSayHandlers extends HasHandlers {
    HandlerRegistration addSayHandler(SayHandler handler);
  }

  public interface SayHandler extends EventHandler {
    public void onSay(SayEvent event);
  }

  private static final Type<SayHandler> TYPE = new Type<SayHandler>();

  public static void fire(HasHandlers source, java.lang.String message) {
    source.fireEvent(new SayEvent(message));
  }

  public static Type<SayHandler> getType() {
    return TYPE;
  }

  java.lang.String message;

  public SayEvent(java.lang.String message) {
    this.message = message;
  }

  protected SayEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<SayHandler> getAssociatedType() {
    return TYPE;
  }

  public java.lang.String getMessage() {
    return message;
  }

  @Override
  protected void dispatch(SayHandler handler) {
    handler.onSay(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    SayEvent other = (SayEvent) obj;
    if (message == null) {
      if (other.message != null)
        return false;
    } else if (!message.equals(other.message))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (message == null ? 1 : message.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "SayEvent["
                 + message
    + "]";
  }
}
