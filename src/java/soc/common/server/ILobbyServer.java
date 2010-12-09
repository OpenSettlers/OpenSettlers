package soc.common.server;

import soc.common.actions.lobby.LobbyAction;

public interface ILobbyServer extends IServer
{
    public void sendLobbyAction(LobbyAction action);
}
