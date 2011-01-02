package soc.common.actions.lobby;

import soc.common.server.data.Player;

public abstract class AbstractLobbyAction implements LobbyAction
{
    private static final long serialVersionUID = -8347682806796145234L;
    protected Player player;

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

}
