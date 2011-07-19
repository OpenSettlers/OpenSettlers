package org.soc.common.server;

import org.soc.common.game.UserList;

public interface Lobby
{
    public UserList getUsers();

    public GameList getGames();

    public LobbyLog getLog();
}
