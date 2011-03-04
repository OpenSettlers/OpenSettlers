package soc.common.lobby;

import soc.common.game.UserList;

public interface Lobby
{
    public UserList getUsers();

    public GameList getGames();

    public LobbyLog getLog();
}
