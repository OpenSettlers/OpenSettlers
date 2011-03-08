package soc.common.lobby;

import soc.common.server.entities.UserList;

public interface Lobby
{
    public UserList getUsers();

    public GameList getGames();

    public LobbyLog getLog();
}
