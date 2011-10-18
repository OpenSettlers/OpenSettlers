package org.soc.common.presenters;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class DiscardCardsEvent extends GwtEvent<DiscardCardsEvent.DiscardCardsHandler> { 

  public interface HasDiscardCardsHandlers extends HasHandlers {
    HandlerRegistration addDiscardCardsHandler(DiscardCardsHandler handler);
  }

  public interface DiscardCardsHandler extends EventHandler {
    public void onDiscardCards(DiscardCardsEvent event);
  }

  private static final Type<DiscardCardsHandler> TYPE = new Type<DiscardCardsHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resources.ResourceList cards) {
    source.fireEvent(new DiscardCardsEvent(cards));
  }

  public static Type<DiscardCardsHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resources.ResourceList cards;

  public DiscardCardsEvent(org.soc.common.game.Resources.ResourceList cards) {
    this.cards = cards;
  }

  protected DiscardCardsEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<DiscardCardsHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resources.ResourceList getCards() {
    return cards;
  }

  @Override
  protected void dispatch(DiscardCardsHandler handler) {
    handler.onDiscardCards(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    DiscardCardsEvent other = (DiscardCardsEvent) obj;
    if (cards == null) {
      if (other.cards != null)
        return false;
    } else if (!cards.equals(other.cards))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (cards == null ? 1 : cards.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DiscardCardsEvent["
                 + cards
    + "]";
  }
}
