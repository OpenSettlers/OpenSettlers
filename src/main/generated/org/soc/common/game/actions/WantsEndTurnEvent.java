package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsEndTurnEvent extends GwtEvent<WantsEndTurnEvent.WantsEndTurnHandler> { 

  public interface HasWantsEndTurnHandlers extends HasHandlers {
    HandlerRegistration addWantsEndTurnHandler(WantsEndTurnHandler handler);
  }

  public interface WantsEndTurnHandler extends EventHandler {
    public void onWantsEndTurn(WantsEndTurnEvent event);
  }

  private static final Type<WantsEndTurnHandler> TYPE = new Type<WantsEndTurnHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsEndTurnEvent());
  }

  public static Type<WantsEndTurnHandler> getType() {
    return TYPE;
  }


  public WantsEndTurnEvent() {
  }

  @Override
  public Type<WantsEndTurnHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsEndTurnHandler handler) {
    handler.onWantsEndTurn(this);
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
    return "WantsEndTurnEvent["
    + "]";
  }
}
