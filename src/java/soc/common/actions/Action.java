package soc.common.actions;

import java.io.Serializable;
import java.util.Date;

import soc.common.game.Player;

public interface Action extends Serializable
{
    /**
     * @return When an action is required to perform for a user,
     * this message is displayed
     */
    public String getToDoMessage();
    
    /**
     * @return DateTime when this action is performed 
     */
    public Date getDateTimeExecuted();
    
    /**
     * @return ID of the player initiating this action
     * ID of 0 means the server initiated this action
     */
    public int getSender();
    
    /**
     * @param sender the sender to set
     */
    public Action setSender(int sender);
    
    /**
     * @return Message set after this action is performed
     */
    public String getMessage();
    
    /**
     * @return the player
     */
    public Player getPlayer();
    
    /**
     * @param player the player to set
     */
    public Action setPlayer(Player player);
    

    
    public String getName();

}
