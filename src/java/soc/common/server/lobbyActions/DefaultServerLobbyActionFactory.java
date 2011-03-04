package soc.common.server.lobbyActions;

import soc.common.actions.lobby.LobbyAction;
import soc.common.server.LobbyServer;

public class DefaultServerLobbyActionFactory implements
        ServerLobbyActionFactory
{
    private LobbyServer lobbyServer;

    public DefaultServerLobbyActionFactory(LobbyServer lobbyServer)
    {
        super();
        this.lobbyServer = lobbyServer;
    }

    @Override
    public ServerLobbyAction createJoinedAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServerLobbyAction createGameRemovedAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServerLobbyAction createLobbyChatAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServerLobbyAction createGameStatusChangedAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServerLobbyAction createNewGameAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServerLobbyAction createDefaultAction(LobbyAction lobbyAction)
    {
        return new DefaultServerLobbyAction(lobbyAction, lobbyServer);
    }

}
