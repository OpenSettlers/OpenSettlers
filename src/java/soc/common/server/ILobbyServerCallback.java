package soc.common.server;

import soc.common.actions.lobby.AbstractLobbyAction;

public interface ILobbyServerCallback
{
    public void receive(AbstractLobbyAction action);
}
