package soc.common.server;

import soc.common.actions.lobby.LobbyAction;

public interface LobbyServerCallback
{
    public void receive(LobbyAction action);
}
