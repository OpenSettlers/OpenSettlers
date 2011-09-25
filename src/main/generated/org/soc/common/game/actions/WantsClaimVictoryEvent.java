package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsClaimVictoryEvent extends GwtEvent<WantsClaimVictoryEvent.WantsClaimVictoryHandler> { 

  public interface HasWantsClaimVictoryHandlers extends HasHandlers {
    HandlerRegistration addWantsClaimVictoryHandler(WantsClaimVictoryHandler handler);
  }

  public interface WantsClaimVictoryHandler extends EventHandler {
    public void onWantsClaimVictory(WantsClaimVictoryEvent event);
  }

  private static final Type<WantsClaimVictoryHandler> TYPE = new Type<WantsClaimVictoryHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsClaimVictoryEvent());
  }

  public static Type<WantsClaimVictoryHandler> getType() {
    return TYPE;
  }


  public WantsClaimVictoryEvent() {
  }

  @Override
  public Type<WantsClaimVictoryHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsClaimVictoryHandler handler) {
    handler.onWantsClaimVictory(this);
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
    return "WantsClaimVictoryEvent["
    + "]";
  }
}
