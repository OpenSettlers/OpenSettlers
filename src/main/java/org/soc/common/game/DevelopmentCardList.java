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
  private static final long serialVersionUID = 3119667710831059077L;
  private List<DevelopmentCard> devCards = new ArrayList<DevelopmentCard>();
  private transient SimpleEventBus eventBus;

  public void add(DevelopmentCard card) {
    devCards.add(card);
    eventBus.fireEvent(new DevelopmentCardsChangedEvent(card, null));
  }
  public void remove(DevelopmentCard cardToRemove) {
    devCards.remove(cardToRemove);
    eventBus.fireEvent(new DevelopmentCardsChangedEvent(null, cardToRemove));
  }
  public static DevelopmentCardList standard()
  {
    DevelopmentCardList standard = new DevelopmentCardList();
    addSoldiers(14, standard);
    addVictoryPoints(5, standard);
    addRoadBuildingss(2, standard);
    addMonopolies(2, standard);
    addYeorOfPlenties(2, standard);
    return standard;
  }
  private static void addSoldiers(int amount, DevelopmentCardList devs) {
    for (int i = 0; i < amount; i++) {
      devs.add(new Soldier());
    }
  }
  private static void addVictoryPoints(int amount, DevelopmentCardList devs) {
    for (int i = 0; i < amount; i++) {
      devs.add(new VictoryPoint());
    }
  }
  private static void addRoadBuildingss(int amount, DevelopmentCardList devs) {
    for (int i = 0; i < amount; i++) {
      devs.add(new RoadBuilding());
    }
  }
  private static void addMonopolies(int amount, DevelopmentCardList devs) {
    for (int i = 0; i < amount; i++) {
      devs.add(new Monopoly());
    }
  }
  private static void addYeorOfPlenties(int amount, DevelopmentCardList devs) {
    for (int i = 0; i < amount; i++) {
      devs.add(new YearOfPlenty());
    }
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
  //  public HandlerRegistration addDevelopmentCardsChangedEventHandler(
  //          DevelopmentCardsChangedEventHandler handler)
  //  {
  //    return getEventBus().addHandler(DevelopmentCardsChangedEvent.TYPE,
  //            handler);
  //  }
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
