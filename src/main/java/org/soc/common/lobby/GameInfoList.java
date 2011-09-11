package org.soc.common.lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.soc.common.lobby.GameListChangedEvent.GameListChangedHandler;
import org.soc.common.lobby.GameListChangedEvent.HasGameListChangedHandlers;
import org.soc.common.server.GameDto;
import org.soc.common.server.entities.User;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/*
 * Represents a list of games with their users
 */
public class GameInfoList implements Iterable<GameDto>, HasGameListChangedHandlers {
  private Map<User, GameDto> games = new HashMap<User, GameDto>();
  private Lobby lobby;
  private transient EventBus eventBus;

  public GameInfoList(Lobby lobby, EventBus eventBus) {
    super();
    this.lobby = lobby;
    this.eventBus = eventBus;
  }
  @Override public Iterator<GameDto> iterator() {
    return games.values().iterator();
  }
  /* Adds given game to this list */
  public void addGame(GameDto game) {
    games.put(game.getHost(), game);
    //    eventBus.fireEvent(new GameListChangedEvent(game, null));
  }
  /* Returns a game with given id if exists. If not, returns null. */
  public GameDto byyId(int id) {
    for (GameDto gameInfo : games.values())
      if (gameInfo.getId() == id)
        return gameInfo;
    return null;
  }
  /* Removes given game instance from this list if contained */
  public void remove(GameDto findById) {
    if (games.values().contains(findById)) {
      games.remove(findById);
      //      eventBus.fireEvent(new GameListChangedEvent(null, findById));
    }
  }
  /* Returns amount of games which are waiting for other players */
  public int getAmountGamesWaiting() {
    int amount = 0;
    for (GameDto game : games.values())
      if (game.getStatus().isWaitingForPlayers())
        amount++;
    return amount;
  }
  /* Returns the game where given user is host. Returns null if no such game exist. */
  public GameDto getByHost(User host) {
    return games.get(host);
  }
  public GameDto byId(int id) {
    for (GameDto gameInfo : games.values())
      if (gameInfo.getId() == id)
        return gameInfo;
    return null;
  }
  /* Returns the GameInfo where given user is currently residing. If no such game exist, returns
   * null. */
  public GameDto getFromGame(User user) {
    for (GameDto game : games.values())
      if (game.getUsers().contains(user))
        return game;
    return null;
  }

  @GenEvent public class GameListChanged {
    @Order(0) GameDto addedGame;
    @Order(1) GameDto removedGame;
  }

  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addGameListChangedHandler(GameListChangedHandler handler) {
    return eventBus.addHandler(GameListChangedEvent.getType(), handler);
  }
  public List<GameDto> toList() {
    return new ArrayList<GameDto>(games.values());
  }
}
