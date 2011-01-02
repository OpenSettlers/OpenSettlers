package soc.common.server;

import soc.common.actions.lobby.LobbyAction;

public interface ILobbyServerCallback
{
    public void receive(LobbyAction action);
}
