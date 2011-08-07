package org.soc.common.server.lobbyActions;

import org.soc.common.actions.lobby.LobbyAction;
import org.soc.common.lobby.Lobby;

public interface ServerLobbyAction
{
    public LobbyAction getLobbyAction();

    public void sendToClients();

    public void perform(Lobby lobby);
}
