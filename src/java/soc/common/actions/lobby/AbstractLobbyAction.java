package soc.common.actions.lobby;

import soc.common.server.entities.Player;

public abstract class AbstractLobbyAction implements LobbyAction
{
    private static final long serialVersionUID = -8347682806796145234L;
    protected Player player;
    protected String message;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.LobbyAction#getPlayer()
     */
    @Override
    public Player getPlayer()
    {
        return player;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
