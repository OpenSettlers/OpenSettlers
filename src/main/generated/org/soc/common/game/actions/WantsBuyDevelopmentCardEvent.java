package org.soc.common.game.actions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsBuyDevelopmentCardEvent extends GwtEvent<WantsBuyDevelopmentCardEvent.WantsBuyDevelopmentCardHandler> { 

  public interface HasWantsBuyDevelopmentCardHandlers extends HasHandlers {
    HandlerRegistration addWantsBuyDevelopmentCardHandler(WantsBuyDevelopmentCardHandler handler);
  }

  public interface WantsBuyDevelopmentCardHandler extends EventHandler {
    public void onWantsBuyDevelopmentCard(WantsBuyDevelopmentCardEvent event);
  }

  private static final Type<WantsBuyDevelopmentCardHandler> TYPE = new Type<WantsBuyDevelopmentCardHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsBuyDevelopmentCardEvent());
  }

  public static Type<WantsBuyDevelopmentCardHandler> getType() {
    return TYPE;
  }


  public WantsBuyDevelopmentCardEvent() {
  }

  @Override
  public Type<WantsBuyDevelopmentCardHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsBuyDevelopmentCardHandler handler) {
    handler.onWantsBuyDevelopmentCard(this);
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
    return "WantsBuyDevelopmentCardEvent["
    + "]";
  }
}
