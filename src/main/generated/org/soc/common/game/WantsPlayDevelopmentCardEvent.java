package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class WantsPlayDevelopmentCardEvent extends GwtEvent<WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler> { 

  public interface HasWantsPlayDevelopmentCardHandlers extends HasHandlers {
    HandlerRegistration addWantsPlayDevelopmentCardHandler(WantsPlayDevelopmentCardHandler handler);
  }

  public interface WantsPlayDevelopmentCardHandler extends EventHandler {
    public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event);
  }

  private static final Type<WantsPlayDevelopmentCardHandler> TYPE = new Type<WantsPlayDevelopmentCardHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new WantsPlayDevelopmentCardEvent());
  }

  public static Type<WantsPlayDevelopmentCardHandler> getType() {
    return TYPE;
  }


  public WantsPlayDevelopmentCardEvent() {
  }

  @Override
  public Type<WantsPlayDevelopmentCardHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WantsPlayDevelopmentCardHandler handler) {
    handler.onWantsPlayDevelopmentCard(this);
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
    return "WantsPlayDevelopmentCardEvent["
    + "]";
  }
}
