package soc.common.server;

import java.util.ArrayList;
import java.util.List;

import soc.common.server.entities.User;

public class LoginResponseImpl implements LoginResponse
{
    private static final long serialVersionUID = -1979935151683745494L;
    private List<LobbyGame> games = new ArrayList<LobbyGame>();
    private List<User> users = new ArrayList<User>();

    @Override
    public List<LobbyGame> getLobbyGames()
    {
        return games;
    }

    @Override
    public List<User> getLoggedInUsers()
    {
        return users;
    }

    public LoginResponseImpl()
    {
    }

    public LoginResponseImpl(List<LobbyGame> games, List<User> users)
    {
        this.games = games;
        this.users = users;
    }

}
