package org.soc.common.server.lobbyActions;

import org.soc.common.actions.lobby.LobbyAction;

public interface ServerLobbyActionFactory
{
    public ServerLobbyAction createJoinedAction(LobbyAction lobbyAction);

    public ServerLobbyAction createGameRemovedAction(LobbyAction lobbyAction);

    public ServerLobbyAction createLobbyChatAction(LobbyAction lobbyAction);

    public ServerLobbyAction createGameStatusChangedAction(
                    LobbyAction lobbyAction);

    public ServerLobbyAction createNewGameAction(LobbyAction lobbyAction);

    public ServerLobbyAction createDefaultAction(LobbyAction lobbyAction);
}
