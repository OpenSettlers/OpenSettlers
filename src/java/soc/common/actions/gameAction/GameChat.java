package soc.common.actions.gameAction;

import soc.common.game.Game;

/*
 * A player is saying something in this game using a text chat interface
 */
public class GameChat extends GameAction
{
    private String chatMessage;

    /**
     * @return the chatMessage
     */
    public String getChatMessage()
    {
        return chatMessage;
    }

    /**
     * @param chatMessage the chatMessage to set
     */
    public GameChat setChatMessage(String chatMessage)
    {
        this.chatMessage = chatMessage;
    
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        // Add the chat message to the chat log of this game
        game.getChatLog().say(this);

        super.perform(game);
    }
    
    
}
