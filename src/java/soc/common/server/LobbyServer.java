package soc.common.server;

import soc.common.actions.lobby.AbstractLobbyAction;

public interface LobbyServer extends Server
{
    public void sendLobbyAction(AbstractLobbyAction action);
}
