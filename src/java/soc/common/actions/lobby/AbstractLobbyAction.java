package soc.common.actions.lobby;

import soc.common.server.entities.Player;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;

public abstract class AbstractLobbyAction implements LobbyAction
{
    private static final long serialVersionUID = -8347682806796145234L;
    protected String message;
    public Player player;

    @Override
    public String getMessage()
    {
        return message;
    }

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.lobby.LobbyAction#setPlayer(soc.common.server.entities
     * .Player)
     */
    @Override
    public LobbyAction setPlayer(Player player)
    {
        this.player = player;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.lobby.LobbyAction#createServerLobbyAction(soc.common
     * .server.lobbyActions.ServerLobbyActionFactory)
     */
    @Override
    public ServerLobbyAction createServerLobbyAction(
            ServerLobbyActionFactory factory)
    {
        return factory.createDefaultAction(this);
    }
}
