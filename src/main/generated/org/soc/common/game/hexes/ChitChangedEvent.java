package org.soc.common.game.hexes;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ChitChangedEvent extends GwtEvent<ChitChangedEvent.ChitChangedHandler> { 

  public interface HasChitChangedHandlers extends HasHandlers {
    HandlerRegistration addChitChangedHandler(ChitChangedHandler handler);
  }

  public interface ChitChangedHandler extends EventHandler {
    public void onChitChanged(ChitChangedEvent event);
  }

  private static final Type<ChitChangedHandler> TYPE = new Type<ChitChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Chit chit, boolean check) {
    source.fireEvent(new ChitChangedEvent(chit, check));
  }

  public static void fire(HasHandlers source, org.soc.common.game.Chit chit) {
    source.fireEvent(new ChitChangedEvent(chit));
  }

  public static Type<ChitChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Chit chit;
  boolean check;

  public ChitChangedEvent(org.soc.common.game.Chit chit, boolean check) {
    this.chit = chit;
    this.check = check;
  }

  public ChitChangedEvent(org.soc.common.game.Chit chit) {
    this.chit = chit;
  }

  protected ChitChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ChitChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Chit getChit() {
    return chit;
  }

  public boolean isCheck() {
    return check;
  }

  @Override
  protected void dispatch(ChitChangedHandler handler) {
    handler.onChitChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ChitChangedEvent other = (ChitChangedEvent) obj;
    if (chit == null) {
      if (other.chit != null)
        return false;
    } else if (!chit.equals(other.chit))
      return false;
    if (check != other.check)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (chit == null ? 1 : chit.hashCode());
    hashCode = (hashCode * 37) + new Boolean(check).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "ChitChangedEvent["
                 + chit
                 + ","
                 + check
    + "]";
  }
}
