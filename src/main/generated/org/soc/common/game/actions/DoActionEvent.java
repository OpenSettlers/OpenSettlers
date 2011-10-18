package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class DoActionEvent extends GwtEvent<DoActionEvent.DoActionHandler> { 

  public interface HasDoActionHandlers extends HasHandlers {
    HandlerRegistration addDoActionHandler(DoActionHandler handler);
  }

  public interface DoActionHandler extends EventHandler {
    public void onDoAction(DoActionEvent event);
  }

  private static final Type<DoActionHandler> TYPE = new Type<DoActionHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new DoActionEvent());
  }

  public static Type<DoActionHandler> getType() {
    return TYPE;
  }


  public DoActionEvent() {
  }

  @Override
  public Type<DoActionHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DoActionHandler handler) {
    handler.onDoAction(this);
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
    return "DoActionEvent["
    + "]";
  }
}
