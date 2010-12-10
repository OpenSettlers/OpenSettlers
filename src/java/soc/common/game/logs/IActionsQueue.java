package soc.common.game.logs;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

/*
 * Represents a queue of GameActions with additional information per GameAction,
 * {@see QueuedAction}.
 */
public interface IActionsQueue
{
    // Enqueues given gameAction
    public void enqueue(GameAction gameAction);
    
    // Enqueues given queuedAction  type
    public void enqueue(QueuedAction queuedAction);
    
    // Removes first occurrence of a queued item 
    public QueuedAction dequeue();
    
    // Returns true when first item in the queue equals given GameAction
    public boolean isExpected(GameAction action, Game game);
    
    // Returns GameAction on top of the queue without removing it 
    public QueuedAction peek();

    // Returns GameAction on top of the queue without removing it 
    public GameAction peekAction();
    
    // Returns amount of queued items
    public int size();
}
