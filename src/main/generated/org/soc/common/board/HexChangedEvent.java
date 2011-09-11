package org.soc.common.board;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class HexChangedEvent extends GwtEvent<HexChangedEvent.HexChangedHandler> { 

  public interface HasHexChangedHandlers extends HasHandlers {
    HandlerRegistration addHexChangedHandler(HexChangedHandler handler);
  }

  public interface HexChangedHandler extends EventHandler {
    public void onHexChanged(HexChangedEvent event);
  }

  private static final Type<HexChangedHandler> TYPE = new Type<HexChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.hexes.Hex oldHex, org.soc.common.game.hexes.Hex newHex) {
    source.fireEvent(new HexChangedEvent(oldHex, newHex));
  }

  public static Type<HexChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.hexes.Hex oldHex;
  org.soc.common.game.hexes.Hex newHex;

  public HexChangedEvent(org.soc.common.game.hexes.Hex oldHex, org.soc.common.game.hexes.Hex newHex) {
    this.oldHex = oldHex;
    this.newHex = newHex;
  }

  protected HexChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<HexChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.hexes.Hex getOldHex() {
    return oldHex;
  }

  public org.soc.common.game.hexes.Hex getNewHex() {
    return newHex;
  }

  @Override
  protected void dispatch(HexChangedHandler handler) {
    handler.onHexChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    HexChangedEvent other = (HexChangedEvent) obj;
    if (oldHex == null) {
      if (other.oldHex != null)
        return false;
    } else if (!oldHex.equals(other.oldHex))
      return false;
    if (newHex == null) {
      if (other.newHex != null)
        return false;
    } else if (!newHex.equals(other.newHex))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (oldHex == null ? 1 : oldHex.hashCode());
    hashCode = (hashCode * 37) + (newHex == null ? 1 : newHex.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "HexChangedEvent["
                 + oldHex
                 + ","
                 + newHex
    + "]";
  }
}
