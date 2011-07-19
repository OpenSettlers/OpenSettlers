package org.soc.common.actions.lobby;

import org.soc.common.lobby.Lobby;

/*
 * Chat action in the main lobby
 */
public class LobbyChat extends AbstractLobbyAction
{
    private static final long serialVersionUID = -722899871562057226L;
    private String chatMessage;

    /** @return the chatMessage */
    public String getChatMessage()
    {
        return chatMessage;
    }

    /** @param chatMessage
     *            the chatMessage to set */
    public LobbyChat setChatMessage(String chatMessage)
    {
        this.chatMessage = chatMessage;

        return this;
    }

    @Override
    public String getMessage()
    {
        return null;
    }

    @Override
    public void perform(Lobby lobby)
    {
        lobby.getLog().addAction(this);
    }

}
