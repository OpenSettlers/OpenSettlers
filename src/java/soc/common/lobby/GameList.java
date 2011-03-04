package soc.common.lobby;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.EventBus;

public class GameList implements Iterable<GameInfo>
{
    private List<GameInfo> games = new ArrayList<GameInfo>();
    private Lobby lobby;
    private EventBus eventBus;

    public GameList(Lobby lobby, EventBus eventBus)
    {
        super();
        this.lobby = lobby;
        this.eventBus = eventBus;
    }

    @Override
    public Iterator<GameInfo> iterator()
    {
        return games.iterator();
    }

    public void addGame(GameInfo game)
    {
        games.add(game);
        eventBus.fireEvent(new GameListChangedEvent(game, null));
    }

    public GameInfo findById(int id)
    {
        for (GameInfo gameInfo : games)
            if (gameInfo.getID() == id)
                return gameInfo;

        return null;
    }

    public void remove(GameInfo findById)
    {
        if (games.contains(findById))
        {
            games.remove(findById);
            eventBus.fireEvent(new GameListChangedEvent(null, findById));
        }
    }

}
