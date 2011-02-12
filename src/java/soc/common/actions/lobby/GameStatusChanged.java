package soc.common.actions.lobby;

import soc.common.server.Lobby;

/*
 * Represents a status change in a game
 */
public class GameStatusChanged extends AbstractLobbyAction
{
    private static final long serialVersionUID = -1626089108739910240L;

    @Override
    public String getMessage()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void perform(Lobby lobby)
    {

    }

}
