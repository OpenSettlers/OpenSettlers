package soc.common.actions.lobby;

import soc.common.internationalization.I18n;
import soc.common.server.Lobby;

/*
 * A player has logged in and joined the server
 */
public class PlayerJoined extends AbstractLobbyAction
{
    private static final long serialVersionUID = -379055883044399903L;

    @Override
    public String getMessage()
    {
        return I18n.get().lobby().joined(player.getName());
    }

    @Override
    public void perform(Lobby lobby)
    {
        lobby.addPlayer(player);
    }

}
