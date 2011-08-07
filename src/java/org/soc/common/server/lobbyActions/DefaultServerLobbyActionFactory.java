package org.soc.common.server.lobbyActions;

import org.soc.common.actions.lobby.LobbyAction;
import org.soc.common.server.LobbyServer;

public class DefaultServerLobbyActionFactory implements
                ServerLobbyActionFactory
{
    private LobbyServer lobbyServer;

    public DefaultServerLobbyActionFactory(LobbyServer lobbyServer)
    {
        super();
        this.lobbyServer = lobbyServer;
    }

    @Override
    public ServerLobbyAction createJoinedAction(LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

    @Override
    public ServerLobbyAction createGameRemovedAction(LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

    @Override
    public ServerLobbyAction createLobbyChatAction(LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

    @Override
    public ServerLobbyAction createGameStatusChangedAction(
                    LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

    @Override
    public ServerLobbyAction createNewGameAction(LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

    @Override
    public ServerLobbyAction createDefaultAction(LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

}
