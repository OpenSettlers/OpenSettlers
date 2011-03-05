package soc.common.server;

import soc.common.actions.lobby.LobbyAction;
import soc.common.server.entities.User;

public interface LobbyServer extends Server
{
    public void executeLobbyAction(LobbyAction lobbyAction);

    public abstract void sendToAllExcept(User excludedUser, LobbyAction action);

    public abstract void sendToAll(LobbyAction action);
}
