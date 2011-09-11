package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.OrderChangedEvent.HasOrderChangedHandlers;
import org.soc.common.game.OrderChangedEvent.OrderChangedHandler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;

public class GamePlayerList implements Iterable<GamePlayer>, Serializable, HasOrderChangedHandlers {
  @GenEvent public class OrderChanged {}

  private List<GamePlayer> players = new ArrayList<GamePlayer>();
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  @Override public Iterator<GamePlayer> iterator() {
    return players.iterator();
  }
  public GamePlayer get(int index) {
    return players.get(index);
  }
  public void add(GamePlayer player) {
    players.add(player);
  }
  public int size() {
    return players.size();
  }
  public int indexOf(GamePlayer player) {
    return players.indexOf(player);
  }
  /* Reorders the players, by setting the given player on first position while maintaining the order */
  public void setStartPlayer(GamePlayer starter) {
    int index = players.indexOf(starter);
    // Only reorder players when starter is not already at first place
    if (index != 0) {
      for (int i = 0; i < index; i++) {
        // Grab player at first position
        GamePlayer toMove = players.get(0);
        // remove it from the list
        players.remove(toMove);
        // add player back to the last position
        players.add(toMove);
      }
      //      eventBus.fireEvent(new OrderChangedEvent());
    }
  }
  public GamePlayerList opponents(GamePlayer ofPlayer) {
    GamePlayerList opponents = new GamePlayerList();
    for (GamePlayer opponent : players)
      if (!opponent.equals(ofPlayer))
        opponents.add(opponent);
    return opponents;
  }
  public boolean contains(GamePlayer player) {
    return players.contains(player);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addOrderChangedHandler(OrderChangedHandler handler) {
    return eventBus.addHandler(OrderChangedEvent.getType(), handler);
  }
}
