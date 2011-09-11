package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class OrderChangedEvent extends GwtEvent<OrderChangedEvent.OrderChangedHandler> { 

  public interface HasOrderChangedHandlers extends HasHandlers {
    HandlerRegistration addOrderChangedHandler(OrderChangedHandler handler);
  }

  public interface OrderChangedHandler extends EventHandler {
    public void onOrderChanged(OrderChangedEvent event);
  }

  private static final Type<OrderChangedHandler> TYPE = new Type<OrderChangedHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new OrderChangedEvent());
  }

  public static Type<OrderChangedHandler> getType() {
    return TYPE;
  }


  public OrderChangedEvent() {
  }

  @Override
  public Type<OrderChangedHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(OrderChangedHandler handler) {
    handler.onOrderChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "OrderChangedEvent["
    + "]";
  }
}
