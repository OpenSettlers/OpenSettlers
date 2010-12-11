package soc.common.actions.gameAction;

import java.util.Date;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.User;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

/*
 * A GameAction performed in a game
 */
public class GameAction
{
    private Player player;
    private int sender;
    protected String invalidMessage;
    protected String toDoMessage;
    protected String message;
    
    /*
     * Should be omitted at hashCode calculation, since values differ at server
     * and at client
     */
    protected Date dateTimeExecuted;

    /**
     * @return When an action is required to perform for a user,
     * this message is displayed
     */
    public String getToDoMessage()
    {
        return toDoMessage;
    }
    
    /**
     * @return DateTime when this action is performed 
     */
    public Date getDateTimeExecuted()
    {
        return dateTimeExecuted;
    }

    /**
     * @return ID of the player initiating this action
     * ID of 0 means the server initiated this action
     */
    public int getSender()
    {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public GameAction setSender(int sender)
    {
        this.sender = sender;
    
        return  this;
    }

    /**
     * @return Message explaining why this action is in invalid state
     */
    public String getInvalidMessage()
    {
        return invalidMessage;
    }


    /**
     * @return Message set after this action is performed
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @return the player
     */
    public Player getPlayer()
    {
        if (sender == 0 && player == null)
        {
            User p = new Player()
                .setId(0)
                .setName("Server");
            
            player = (Player)p;
        }
        return player;
    }
    
    
    /**
     * @param player the player to set
     */
    public GameAction setPlayer(Player player)
    {
        this.player = player;
        this.sender = player.getId();        

        return this;
    }

    /*
     * Subclasses should call this method after they have performed
     * their specific implementation (at the end of the method)
     */
    public void perform(Game game)
    {
        // Set the dateTime when this action is performed
        dateTimeExecuted = new Date();
        
        // Add this action to the gamelog
        game.getGameLog().addAction(this);
    }

    /* 
     * Returns true if player is allowed to play this action in given TurnPhase
     */
    public boolean isAllowed(TurnPhase turnPhase)
    {
        throw new RuntimeException("Not yet implemented");
    }
    
    /* 
     * Returns true if player is allowed to play this action in given GamePhase
     */
    public boolean isAllowed(GamePhase gamePhase)
    {
        throw new RuntimeException("Not yet implemented");
    }
    
    public boolean isValid()
    {
        return true;
    }
}
