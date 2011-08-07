package org.soc.common.server;

import org.soc.common.actions.lobby.LobbyAction;

public interface LobbyServerCallback
{
    public void receive(LobbyAction action);
}
