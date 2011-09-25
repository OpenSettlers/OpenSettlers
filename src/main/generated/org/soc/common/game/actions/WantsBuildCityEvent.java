package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsBuildCityEvent extends GwtEvent<WantsBuildCityEvent.WantsBuildCityHandler> { 

  public interface HasWantsBuildCityHandlers extends HasHandlers {
    HandlerRegistration addWantsBuildCityHandler(WantsBuildCityHandler handler);
  }

  public interface WantsBuildCityHandler extends EventHandler {
    public void onWantsBuildCity(WantsBuildCityEvent event);
  }

  private static final Type<WantsBuildCityHandler> TYPE = new Type<WantsBuildCityHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsBuildCityEvent());
  }

  public static Type<WantsBuildCityHandler> getType() {
    return TYPE;
  }


  public WantsBuildCityEvent() {
  }

  @Override
  public Type<WantsBuildCityHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsBuildCityHandler handler) {
    handler.onWantsBuildCity(this);
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
    return "WantsBuildCityEvent["
    + "]";
  }
}
