package soc.common.game.logs;

import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

import com.google.gwt.event.shared.HandlerRegistration;

/*
 * Represents a queue of GameActions with additional information per GameAction,
 * {@see QueuedAction}.
 */
public interface ActionsQueue
{
    // Enqueues given gameAction
    public void enqueue(GameAction gameAction);

    // Enqueues given queuedAction at nd of queue
    public void enqueue(QueuedAction queuedAction);

    // Enqueues given queuedAction as first to process
    public void enqueuePriority(QueuedAction queuedAction);

    // Removes and returns first item in line to process from the queue
    public QueuedAction dequeue();

    // Assumes given action is expected. Removes and returns first item in line
    // to process from the queue
    public QueuedAction dequeue(GameAction action);

    // Returns true when first item in the queue equals given GameAction
    public GameAction findExpected(GameAction action, Game game);

    // Returns GameAction on top of the queue without removing it
    public QueuedAction peek();

    // Returns GameAction on top of the queue without removing it
    public GameAction peekAction();

    // Returns amount of queued items
    public int size();

    // Returns true if first action in the queue is blocking
    public boolean isWaitingForActions();

    // Returns a list of the actions which must be performed first
    public List<GameAction> getBlockingActions();

    public HandlerRegistration addQueueChangedEventHandler(
            ActionQueueChangedEventHandler handler);
}
