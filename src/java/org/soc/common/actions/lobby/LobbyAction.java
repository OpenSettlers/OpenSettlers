package org.soc.common.actions.lobby;

import java.io.Serializable;

import org.soc.common.actions.ValidateResult;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.entities.User;
import org.soc.common.server.lobbyActions.ServerLobbyAction;
import org.soc.common.server.lobbyActions.ServerLobbyActionFactory;


public interface LobbyAction extends Serializable
{
    public void perform(Lobby lobby);

    public User getUser();

    public String getMessage();

    public LobbyAction setUser(User user);

    public ServerLobbyAction createServerLobbyAction(
                    ServerLobbyActionFactory factory);

    public ValidateResult isValid(Lobby lobby, ValidateResult result);

    public int getUserId();

    public LobbyAction setUserId(int id);
}
