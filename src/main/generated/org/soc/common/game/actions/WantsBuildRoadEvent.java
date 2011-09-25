package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsBuildRoadEvent extends GwtEvent<WantsBuildRoadEvent.WantsBuildRoadHandler> { 

  public interface HasWantsBuildRoadHandlers extends HasHandlers {
    HandlerRegistration addWantsBuildRoadHandler(WantsBuildRoadHandler handler);
  }

  public interface WantsBuildRoadHandler extends EventHandler {
    public void onWantsBuildRoad(WantsBuildRoadEvent event);
  }

  private static final Type<WantsBuildRoadHandler> TYPE = new Type<WantsBuildRoadHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsBuildRoadEvent());
  }

  public static Type<WantsBuildRoadHandler> getType() {
    return TYPE;
  }


  public WantsBuildRoadEvent() {
  }

  @Override
  public Type<WantsBuildRoadHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsBuildRoadHandler handler) {
    handler.onWantsBuildRoad(this);
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
    return "WantsBuildRoadEvent["
    + "]";
  }
}
