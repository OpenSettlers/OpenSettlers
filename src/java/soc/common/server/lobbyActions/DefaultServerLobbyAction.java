package soc.common.server.lobbyActions;

import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.Lobby;
import soc.common.server.LobbyServer;

public class DefaultServerLobbyAction implements ServerLobbyAction
{
    private LobbyAction lobbyAction;
    private LobbyServer lobbyServer;

    public DefaultServerLobbyAction(LobbyAction lobbyAction,
            LobbyServer lobbyServer)
    {
        super();
        this.lobbyAction = lobbyAction;
        this.lobbyServer = lobbyServer;
    }

    @Override
    public LobbyAction getLobbyAction()
    {
        return lobbyAction;
    }

    /*
     * by default, it sends encapsulated LobbyAction to each user on the server
     * 
     * @see soc.common.server.lobbyActions.ServerLobbyAction#sendToClients()
     */
    @Override
    public void sendToClients()
    {
        lobbyServer.executeLobbyAction(lobbyAction);
    }

    @Override
    public void perform(Lobby lobby)
    {
        lobbyAction.perform(lobby);
    }

}
