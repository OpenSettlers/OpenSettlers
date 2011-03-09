package soc.common.server.lobbyActions;

import soc.common.actions.lobby.LobbyAction;
import soc.common.actions.lobby.game.CreateGame;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;
import soc.common.server.LobbyServer;

public class CreateGameServerAction implements ServerLobbyAction
{
    private CreateGame createGame;
    private LobbyServer lobbyServer;

    public CreateGameServerAction(LobbyServer lobbyServer, CreateGame createGame)
    {
        super();
        this.lobbyServer = lobbyServer;
        this.createGame = createGame;
    }

    @Override
    public LobbyAction getLobbyAction()
    {
        return createGame;
    }

    @Override
    public void sendToClients()
    {
        lobbyServer.sendToAll(createGame);
    }

    @Override
    public void perform(Lobby lobby)
    {
        // Grab current gameroom user is in, when present
        GameInfo exisitingGame = lobbyServer.getLobby().getGames()
                        .getByHost(createGame.getUser());

        // Perform the action when valid or bugger out when the user is already in some room
        if (exisitingGame == null)
            createGame.perform(lobby);
        else
            lobbyServer.sayError(createGame.getUser(), "Already in a game room");
    }

}
