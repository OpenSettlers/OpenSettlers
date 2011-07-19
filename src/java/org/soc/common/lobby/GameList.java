package org.soc.common.lobby;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.soc.common.server.entities.User;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;

/*
 * Represents a list of games with their users
 */
public class GameList implements Iterable<GameInfo>
{
    private Map<User, GameInfo> games = new HashMap<User, GameInfo>();
    private Lobby lobby;
    private transient EventBus eventBus;

    public GameList(Lobby lobby, EventBus eventBus)
    {
        super();
        this.lobby = lobby;
        this.eventBus = eventBus;
    }

    @Override
    public Iterator<GameInfo> iterator()
    {
        return games.values().iterator();
    }

    /*
     * Adds given game to this list
     */
    public void addGame(GameInfo game)
    {
        games.put(game.getHost(), game);
        eventBus.fireEvent(new GameListChangedEvent(game, null));
    }

    /*
     * Returns a game with given id if exists. If not, returns null.
     */
    public GameInfo findById(int id)
    {
        for (GameInfo gameInfo : games.values())
            if (gameInfo.getID() == id)
                return gameInfo;

        return null;
    }

    /*
     * Removes given game instance from this list if contained
     */
    public void remove(GameInfo findById)
    {
        if (games.values().contains(findById))
        {
            games.remove(findById);
            eventBus.fireEvent(new GameListChangedEvent(null, findById));
        }
    }

    public HandlerRegistration addGameListChangedEventHandler(
                    GameListChangedEventHandler handler)
    {
        return eventBus.addHandler(GameListChangedEvent.TYPE, handler);
    }

    /*
     * Returns amount of games which are waiting for other players
     */
    public int getAmountGamesWaiting()
    {
        int amount = 0;

        for (GameInfo game : games.values())
            if (game.getGameStatus().isWaitingForPlayers())
                amount++;

        return amount;
    }

    /*
     * Returns the game where given user is host. Returns null if no such game exist.
     */
    public GameInfo getByHost(User host)
    {
        return games.get(host);
    }

    public GameInfo getById(int id)
    {
        for (GameInfo gameInfo : games.values())
            if (gameInfo.getID() == id)
                return gameInfo;

        return null;
    }

    /*
     * Returns the GameInfo where given user is currently residing. If no such game exist, returns
     * null.
     */
    public GameInfo getFromGame(User user)
    {
        for (GameInfo game : games.values())
            if (game.getUsers().contains(user))
                return game;

        return null;
    }
}
