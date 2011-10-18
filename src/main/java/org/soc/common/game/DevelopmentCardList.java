package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.DevelopmentCard.Monopoly;
import org.soc.common.game.DevelopmentCard.RoadBuilding;
import org.soc.common.game.DevelopmentCard.Soldier;
import org.soc.common.game.DevelopmentCard.VictoryPoint;
import org.soc.common.game.DevelopmentCard.YearOfPlenty;
import org.soc.common.game.DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler;
import org.soc.common.game.DevelopmentCardsChangedEvent.HasDevelopmentCardsChangedHandlers;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class DevelopmentCardList implements Iterable<DevelopmentCard>,
        Serializable, HasDevelopmentCardsChangedHandlers
{
  private List<DevelopmentCard> devCards = new ArrayList<DevelopmentCard>();
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  public void add(DevelopmentCard card) {
    devCards.add(card);
    eventBus.fireEvent(new DevelopmentCardsChangedEvent(card, null));
  }
  public void remove(DevelopmentCard cardToRemove) {
    devCards.remove(cardToRemove);
    eventBus.fireEvent(new DevelopmentCardsChangedEvent(null, cardToRemove));
  }
  public static DevelopmentCardList newStandard() {
    return new DevelopmentCardList()
            .addSoldiers(14)
            .addVictoryPoints(5)
            .addRoadBuildingss(2)
            .addMonopolies(2)
            .addYeorOfPlenties(2);
  }
  public DevelopmentCardList addSoldiers(int amount) {
    for (int i = 0; i < amount; i++)
      this.add(new Soldier());
    return this;
  }
  public DevelopmentCardList addVictoryPoints(int amount) {
    for (int i = 0; i < amount; i++)
      this.add(new VictoryPoint());
    return this;
  }
  public DevelopmentCardList addRoadBuildingss(int amount) {
    for (int i = 0; i < amount; i++)
      this.add(new RoadBuilding());
    return this;
  }
  public DevelopmentCardList addMonopolies(int amount) {
    for (int i = 0; i < amount; i++)
      this.add(new Monopoly());
    return this;
  }
  public DevelopmentCardList addYeorOfPlenties(int amount) {
    for (int i = 0; i < amount; i++)
      this.add(new YearOfPlenty());
    return this;
  }
  public static DevelopmentCardList extended() {
    DevelopmentCardList result = new DevelopmentCardList();
    for (int i = 0; i < 19; i++)
      result.add(new Soldier());
    for (int i = 0; i < 5; i++)
      result.add(new VictoryPoint());
    for (int i = 0; i < 3; i++)
      result.add(new RoadBuilding());
    for (int i = 0; i < 3; i++)
      result.add(new Monopoly());
    for (int i = 0; i < 3; i++)
      result.add(new YearOfPlenty());
    return result;
  }
  public ArrayList<DevelopmentCard> ofType(DevelopmentCard type) {
    ArrayList<DevelopmentCard> result = new ArrayList<DevelopmentCard>();
    for (DevelopmentCard devcard : this)
      if (devcard.getClass() == type.getClass())
        result.add(devcard);
    return result;
  }
  @Override public Iterator<DevelopmentCard> iterator() {
    return devCards.iterator();
  }
  public int size() {
    return devCards.size();
  }
  public DevelopmentCard drawTop() {
    // Get top developmentcard
    DevelopmentCard result = devCards.get(devCards.size() - 1);
    // remove it from the deck
    devCards.remove(devCards.size() - 1);
    // return the card
    return result;
  }
  private SimpleEventBus getEventBus() {
    if (eventBus == null) {
      eventBus = new SimpleEventBus();
    }
    return eventBus;
  }
  public boolean contains(DevelopmentCard devCard) {
    return devCards.contains(devCard);
  }

  @GenEvent public class DevelopmentCardsChanged {
    @Order(0) DevelopmentCard removedCard;
    @Order(1) DevelopmentCard addedCard;
  }

  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addDevelopmentCardsChangedHandler(
          DevelopmentCardsChangedHandler handler) {
    return eventBus.addHandler(DevelopmentCardsChangedEvent.getType(), handler);
  }
}
