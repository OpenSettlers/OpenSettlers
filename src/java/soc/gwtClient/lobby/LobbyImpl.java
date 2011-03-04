package soc.gwtClient.lobby;

import soc.common.game.UserList;
import soc.common.lobby.GameList;
import soc.common.lobby.Lobby;
import soc.common.lobby.LobbyLog;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class LobbyImpl implements Lobby
{
    private UserList users;
    private GameList games;
    private LobbyLog log;
    private EventBus eventBus = new SimpleEventBus();

    public LobbyImpl()
    {
        games = new GameList(this, eventBus);
        log = new LobbyLog(this, eventBus);
        users = new UserList();
    }

    @Override
    public UserList getUsers()
    {
        return users;
    }

    @Override
    public GameList getGames()
    {
        return games;
    }

    @Override
    public LobbyLog getLog()
    {
        return log;
    }

}
