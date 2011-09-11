package org.soc.common.lobby.actions;

import org.soc.common.internationalization.I;
import org.soc.common.lobby.Lobby;

/*
 * A player has disconnected and left the server
 */
public class PlayerLeft extends AbstractLobbyAction
{
    private static final long serialVersionUID = 1885439014105922967L;

    @Override
    public void perform(Lobby lobby)
    {
        // lobby.getUsers().re.removePlayer(player);
    }

    @Override
    public String getMessage()
    {
        return I.get().lobby().left(user.name());
    }

}
