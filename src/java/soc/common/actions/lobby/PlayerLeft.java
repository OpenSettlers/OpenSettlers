package soc.common.actions.lobby;

import soc.common.internationalization.I18n;
import soc.common.lobby.Lobby;

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
        return I18n.get().lobby().left(user.getName());
    }

}
