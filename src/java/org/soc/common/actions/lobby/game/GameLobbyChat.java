package org.soc.common.actions.lobby.game;

import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.Lobby;

/*
 * Chat on the game room while in LobbyGamePhase
 */
public class GameLobbyChat extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = 2613193794986293870L;
    private String chatMessage = "";

    @Override
    public void perform(Lobby lobby)
    {
        GameInfo game = lobby.getGames().findById(gameId);
        if (game != null)
            game.getLobbyLog().addAction(this);
    }

    public GameLobbyChat setChatMessage(String chatMessage)
    {
        this.chatMessage = chatMessage;
        return this;
    }

    public String getChatMessage()
    {
        return chatMessage;
    }

}
