package org.soc.common.presenters;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class CardPickedEvent extends GwtEvent<CardPickedEvent.CardPickedHandler> { 

  public interface HasCardPickedHandlers extends HasHandlers {
    HandlerRegistration addCardPickedHandler(CardPickedHandler handler);
  }

  public interface CardPickedHandler extends EventHandler {
    public void onCardPicked(CardPickedEvent event);
  }

  private static final Type<CardPickedHandler> TYPE = new Type<CardPickedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resource card) {
    source.fireEvent(new CardPickedEvent(card));
  }

  public static Type<CardPickedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resource card;

  public CardPickedEvent(org.soc.common.game.Resource card) {
    this.card = card;
  }

  protected CardPickedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<CardPickedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resource getCard() {
    return card;
  }

  @Override
  protected void dispatch(CardPickedHandler handler) {
    handler.onCardPicked(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    CardPickedEvent other = (CardPickedEvent) obj;
    if (card == null) {
      if (other.card != null)
        return false;
    } else if (!card.equals(other.card))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (card == null ? 1 : card.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "CardPickedEvent["
                 + card
    + "]";
  }
}
