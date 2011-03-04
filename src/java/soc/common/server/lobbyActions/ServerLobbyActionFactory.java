package soc.common.server.lobbyActions;

import soc.common.actions.lobby.LobbyAction;

public interface ServerLobbyActionFactory
{
    public ServerLobbyAction createJoinedAction();

    public ServerLobbyAction createGameRemovedAction();

    public ServerLobbyAction createLobbyChatAction();

    public ServerLobbyAction createGameStatusChangedAction();

    public ServerLobbyAction createNewGameAction();

    public ServerLobbyAction createDefaultAction(LobbyAction lobbyAction);
}
