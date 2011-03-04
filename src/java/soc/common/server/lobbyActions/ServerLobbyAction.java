package soc.common.server.lobbyActions;

import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.Lobby;

public interface ServerLobbyAction
{
    public LobbyAction getLobbyAction();

    public void sendToClients();

    public void perform(Lobby lobby);
}
