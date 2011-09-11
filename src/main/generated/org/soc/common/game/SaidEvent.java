package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class SaidEvent extends GwtEvent<SaidEvent.SaidHandler> { 

  public interface HasSaidHandlers extends HasHandlers {
    HandlerRegistration addSaidHandler(SaidHandler handler);
  }

  public interface SaidHandler extends EventHandler {
    public void onSaid(SaidEvent event);
  }

  private static final Type<SaidHandler> TYPE = new Type<SaidHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.actions.GameChat said) {
    source.fireEvent(new SaidEvent(said));
  }

  public static Type<SaidHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.actions.GameChat said;

  public SaidEvent(org.soc.common.game.actions.GameChat said) {
    this.said = said;
  }

  protected SaidEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<SaidHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.actions.GameChat getSaid() {
    return said;
  }

  @Override
  protected void dispatch(SaidHandler handler) {
    handler.onSaid(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    SaidEvent other = (SaidEvent) obj;
    if (said == null) {
      if (other.said != null)
        return false;
    } else if (!said.equals(other.said))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (said == null ? 1 : said.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "SaidEvent["
                 + said
    + "]";
  }
}
