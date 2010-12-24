package soc.common.server;

import soc.common.actions.lobby.AbstractLobbyAction;

public interface ILobbyServer extends IServer
{
    public void sendLobbyAction(AbstractLobbyAction action);
}
