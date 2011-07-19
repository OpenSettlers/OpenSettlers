package org.soc.common.lobby;

import org.soc.common.server.entities.UserList;

public interface Lobby
{
    public UserList getUsers();

    public GameList getGames();

    public LobbyLog getLog();
}
