package org.soc.common.lobby.actions;

import org.soc.common.internationalization.I;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.entities.User;

/*
 * A player has logged in and joined the server
 */
public class PlayerJoined extends AbstractLobbyAction
{
    private static final long serialVersionUID = -379055883044399903L;
    private User joinedUser;

    @Override
    public String getMessage()
    {
        return I.get().lobby().joined(user.name());
    }

    @Override
    public void perform(Lobby lobby)
    {
        lobby.getUsers().addUser(joinedUser);
    }

    public PlayerJoined setJoinedUser(User joinedUser)
    {
        this.joinedUser = joinedUser;
        return this;
    }

    public User getJoinedUser()
    {
        return joinedUser;
    }

}
