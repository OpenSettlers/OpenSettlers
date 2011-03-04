package soc.common.server;

import soc.common.actions.lobby.LobbyAction;

public interface LobbyServer extends Server
{
    public void executeLobbyAction(LobbyAction lobbyAction);
}
