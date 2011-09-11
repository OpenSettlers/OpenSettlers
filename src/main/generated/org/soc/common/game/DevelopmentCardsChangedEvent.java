package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class DevelopmentCardsChangedEvent extends GwtEvent<DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler> { 

  public interface HasDevelopmentCardsChangedHandlers extends HasHandlers {
    HandlerRegistration addDevelopmentCardsChangedHandler(DevelopmentCardsChangedHandler handler);
  }

  public interface DevelopmentCardsChangedHandler extends EventHandler {
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event);
  }

  private static final Type<DevelopmentCardsChangedHandler> TYPE = new Type<DevelopmentCardsChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.DevelopmentCard removedCard, org.soc.common.game.DevelopmentCard addedCard) {
    source.fireEvent(new DevelopmentCardsChangedEvent(removedCard, addedCard));
  }

  public static Type<DevelopmentCardsChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.DevelopmentCard removedCard;
  org.soc.common.game.DevelopmentCard addedCard;

  public DevelopmentCardsChangedEvent(org.soc.common.game.DevelopmentCard removedCard, org.soc.common.game.DevelopmentCard addedCard) {
    this.removedCard = removedCard;
    this.addedCard = addedCard;
  }

  protected DevelopmentCardsChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<DevelopmentCardsChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.DevelopmentCard getRemovedCard() {
    return removedCard;
  }

  public org.soc.common.game.DevelopmentCard getAddedCard() {
    return addedCard;
  }

  @Override
  protected void dispatch(DevelopmentCardsChangedHandler handler) {
    handler.onDevelopmentCardsChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    DevelopmentCardsChangedEvent other = (DevelopmentCardsChangedEvent) obj;
    if (removedCard == null) {
      if (other.removedCard != null)
        return false;
    } else if (!removedCard.equals(other.removedCard))
      return false;
    if (addedCard == null) {
      if (other.addedCard != null)
        return false;
    } else if (!addedCard.equals(other.addedCard))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (removedCard == null ? 1 : removedCard.hashCode());
    hashCode = (hashCode * 37) + (addedCard == null ? 1 : addedCard.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DevelopmentCardsChangedEvent["
                 + removedCard
                 + ","
                 + addedCard
    + "]";
  }
}
