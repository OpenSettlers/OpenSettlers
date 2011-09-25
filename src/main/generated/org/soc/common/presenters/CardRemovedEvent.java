package org.soc.common.presenters;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class CardRemovedEvent extends GwtEvent<CardRemovedEvent.CardRemovedHandler> { 

  public interface HasCardRemovedHandlers extends HasHandlers {
    HandlerRegistration addCardRemovedHandler(CardRemovedHandler handler);
  }

  public interface CardRemovedHandler extends EventHandler {
    public void onCardRemoved(CardRemovedEvent event);
  }

  private static final Type<CardRemovedHandler> TYPE = new Type<CardRemovedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resource card) {
    source.fireEvent(new CardRemovedEvent(card));
  }

  public static Type<CardRemovedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resource card;

  public CardRemovedEvent(org.soc.common.game.Resource card) {
    this.card = card;
  }

  protected CardRemovedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<CardRemovedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resource getCard() {
    return card;
  }

  @Override
  protected void dispatch(CardRemovedHandler handler) {
    handler.onCardRemoved(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    CardRemovedEvent other = (CardRemovedEvent) obj;
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
    return "CardRemovedEvent["
                 + card
    + "]";
  }
}
