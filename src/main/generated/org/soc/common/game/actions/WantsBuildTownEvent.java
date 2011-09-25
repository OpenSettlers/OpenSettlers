package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsBuildTownEvent extends GwtEvent<WantsBuildTownEvent.WantsBuildTownHandler> { 

  public interface HasWantsBuildTownHandlers extends HasHandlers {
    HandlerRegistration addWantsBuildTownHandler(WantsBuildTownHandler handler);
  }

  public interface WantsBuildTownHandler extends EventHandler {
    public void onWantsBuildTown(WantsBuildTownEvent event);
  }

  private static final Type<WantsBuildTownHandler> TYPE = new Type<WantsBuildTownHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsBuildTownEvent());
  }

  public static Type<WantsBuildTownHandler> getType() {
    return TYPE;
  }


  public WantsBuildTownEvent() {
  }

  @Override
  public Type<WantsBuildTownHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsBuildTownHandler handler) {
    handler.onWantsBuildTown(this);
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
    return "WantsBuildTownEvent["
    + "]";
  }
}
