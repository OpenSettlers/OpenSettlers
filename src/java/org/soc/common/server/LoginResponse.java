package org.soc.common.server;

import java.io.Serializable;
import java.util.List;

import org.soc.common.server.entities.User;


public interface LoginResponse extends Serializable
{
    public List<User> getLoggedInUsers();

    public List<LobbyGame> getLobbyGames();
}
