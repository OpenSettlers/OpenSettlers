package soc.common.actions.gameAction;

import soc.common.game.Game;

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
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        game.getChatLog().say(this);

        super.perform(game);
    }
    
    
}
