package soc.common.game.logs;

import soc.common.actions.gameAction.GameAction;

/*
 * GameAction which is queued. Allows annotation of a GameAction with additional 
 * values, such as isBlocking. 
 * 
 * By default, an action is not blocking nor optional.
 * 
 * For instance, when a 7 is rolled, every player has to wait until all other 
 * players are done loosing excess of 7 cards. In that case, a LooseCards action
 * is added for each player having > 7 cards, with the isBlocking field set to true.
 * 
 * Another example is in the BeforeDiceRolling GamePhase. A player having development-
 * cards may choose to play a soldier or a {@see VictoryPoint} if he has one, or proceed to 
 * the RollDice GamePhase. PlayDevelopmentCard action is added to the queue, but is 
 * set to optional.
 */
public class QueuedAction
{
    private GameAction action;
    private boolean isBlocking = false;
    private boolean isOptional = false;
    
    public QueuedAction()
    {
    }

    /*
     * Creates QueuedAction with default values blocking and optional both false
     */
    public QueuedAction(GameAction action)
    {
        this.action = action;
    }

    public QueuedAction(GameAction action, boolean isBlocking)
    {
        this(action);
        this.isBlocking = isBlocking;
    }
    
    public QueuedAction(GameAction action, boolean isBlocking, boolean isOptional)
    {
        this(action, isBlocking);
        this.isOptional = isOptional;
    }

    /**
     * Whether or not the queued action is optional for a player to perform.
     * An optional action is for example PlayDevelopmentCard during BeforeDiceRoll 
     * GamePhase.
     * @return the isOptional
     */
    public boolean isOptional()
    {
        return isOptional;
    }

    /**
     * Whether or not the queued action is optional for a player to perform.
     * An optional action is for example PlayDevelopmentCard during BeforeDiceRoll 
     * GamePhase.
     * @param isOptional the isOptional to set
     */
    public QueuedAction setOptional(boolean isOptional)
    {
        this.isOptional = isOptional;
    
        return this;
    }

    /**
     * @return the queued action
     */
    public GameAction getAction()
    {
        return action;
    }
    /**
     * @param action the action to set
     */
    public QueuedAction setAction(GameAction action)
    {
        this.action = action;
    
        return this;
    }
    /**
     * @return when set, this queued action must be performed before the game
     * can continue
     */
    public boolean isBlocking()
    {
        return isBlocking;
    }
    /**
     * @param isBlocking the isBlocking to set
     */
    public QueuedAction setBlocking(boolean isBlocking)
    {
        this.isBlocking = isBlocking;
    
        return this;
    }
    
}
