package soc.common.actions.lobby;

import soc.common.internationalization.I18n;
import soc.common.lobby.Lobby;
import soc.common.server.entities.User;

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
        return I18n.get().lobby().joined(user.getName());
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
