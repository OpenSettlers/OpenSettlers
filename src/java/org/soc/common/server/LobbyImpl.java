package org.soc.common.server;

import org.soc.common.game.UserList;
import org.soc.common.server.GameList;
import org.soc.common.server.Lobby;
import org.soc.common.server.LobbyLog;

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
