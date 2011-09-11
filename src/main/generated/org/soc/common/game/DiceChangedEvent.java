package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class DiceChangedEvent extends GwtEvent<DiceChangedEvent.DiceChangedHandler> { 

  public interface HasDiceChangedHandlers extends HasHandlers {
    HandlerRegistration addDiceChangedHandler(DiceChangedHandler handler);
  }

  public interface DiceChangedHandler extends EventHandler {
    public void onDiceChanged(DiceChangedEvent event);
  }

  private static final Type<DiceChangedHandler> TYPE = new Type<DiceChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Dice newDice) {
    source.fireEvent(new DiceChangedEvent(newDice));
  }

  public static Type<DiceChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Dice newDice;

  public DiceChangedEvent(org.soc.common.game.Dice newDice) {
    this.newDice = newDice;
  }

  protected DiceChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<DiceChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Dice getNewDice() {
    return newDice;
  }

  @Override
  protected void dispatch(DiceChangedHandler handler) {
    handler.onDiceChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    DiceChangedEvent other = (DiceChangedEvent) obj;
    if (newDice == null) {
      if (other.newDice != null)
        return false;
    } else if (!newDice.equals(other.newDice))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newDice == null ? 1 : newDice.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DiceChangedEvent["
                 + newDice
    + "]";
  }
}
