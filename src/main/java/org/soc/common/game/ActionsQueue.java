package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.ActionQueueChangedEvent.ActionQueueChangedHandler;
import org.soc.common.game.ActionQueueChangedEvent.HasActionQueueChangedHandlers;
import org.soc.common.game.actions.GameAction;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/** Represents a queue of GameActions with additional information per GameAction, {@see QueuedAction}
 * .
 * 
 * For instance, when a 7 is rolled, every player has to wait until all other players are done
 * loosing excess of 7 cards. In that case, a LooseCards action is added for each player having > 7
 * cards, with blocking set to true. */
public interface ActionsQueue extends Serializable, HasActionQueueChangedHandlers {
  // Enqueues given gameAction
  public void enqueue(GameAction gameAction, boolean blocking);
  // Enqueues given GameAction as first to process
  public void enqueuePriority(GameAction queuedAction, boolean blocking);
  // Assumes given action is expected. Removes and returns first item in line
  // to process from the queue
  public GameAction dequeue();
  // Returns true when first item in the queue equals given GameAction
  public GameAction findExpected(GameAction action);
  // Returns GameAction on top of the queue without removing it
  public GameAction peek();
  // Returns amount of queued items
  public int size();
  public ActionsQueue forceNext(GameAction gameAction);
  // Returns true if first action in the queue is blocking
  public boolean isWaitingForActions();
  // Returns a list of the actions which must be performed first
  public List<GameAction> blockingActions();
  // Returns a list of the actions which must be performed first
  public GameAction blockingActions(GamePlayer player);
  public GameAction dequeueExpected(GameAction expectedAction);
  public boolean hasPendingBotActions();
  public List<GameAction> pendingBotActions();

  @GenEvent public class ActionQueueChanged {
    @Order(0) GameAction dequeuedAction;
    @Order(1) GameAction enqueuedAction;
  }

  /** A list of queued actions. This aids the user in what to expect from them, they can actually see
   * a list of things they must do.
   * 
   * The actions blocking gameplay from advancing can be of three types: 1. The first action in the
   * queue where isServer=true. This action will be executed immediately after the current executing
   * action 2. First encountered action where isBlocking=true 3. All actions of which
   * isBlocking=false */
  public class ActionsQueueImpl implements ActionsQueue {
    private List<QueuedAction> actions = new ArrayList<QueuedAction>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    @Override public void enqueue(GameAction gameAction, boolean blocking) {
      actions.add(new QueuedAction(gameAction, blocking));
      eventBus.fireEvent(new ActionQueueChangedEvent(null, gameAction));
    }
    @Override public GameAction peek() {
      return getFirst() == null ? null : getFirst().gameAction();
    }
    @Override public GameAction dequeue() {
      QueuedAction queuedAction = getFirst();
      actions.remove(queuedAction);
      eventBus.fireEvent(new ActionQueueChangedEvent(queuedAction
              .gameAction(), null));
      return queuedAction.gameAction();
    }
    @Override public GameAction dequeueExpected(GameAction expectedAction) {
      QueuedAction actionToRemove = findExpectedQueuedAction(expectedAction);
      if (actions.remove(actionToRemove)) {
        eventBus.fireEvent(new ActionQueueChangedEvent(actionToRemove
                .gameAction(), null));
        return expectedAction;
      } else {
        throw new AssertionError("Expected given action to be in queue");
      }
    }
    /** Returns true when given action is expected with relation to the gamestate. */
    @Override public GameAction findExpected(GameAction prototype) {
      QueuedAction expected = findExpectedQueuedAction(prototype);
      return expected == null ? null : expected.gameAction();
    }
    private QueuedAction findExpectedQueuedAction(GameAction prototype) {
      // First action which is a server action is always expected
      if (actions.size() > 0 && getFirst().gameAction().isServer())
        return getFirst();
      for (QueuedAction blockingAction : getBlockingQueuedActions()) {
        if (blockingAction.gameAction().isExpectedQueueType(prototype)
                && blockingAction.gameAction().player()
                        .equals(prototype.player()))
          return blockingAction;
      }
      return null;
    }
    @Override public int size() {
      return actions.size();
    }
    @Override public void enqueuePriority(GameAction gameAction, boolean blocking) {
      actions.add(0, new QueuedAction(gameAction, blocking));
      //      eventBus.fireEvent(new ActionQueueChangedEvent(null, gameAction));
    }
    @Override public boolean isWaitingForActions() {
      return getFirst() == null ? false : getFirst().isBlocking();
    }
    /* Returns the first encountered blocking action or all the encountered non-blocking actions */
    public List<GameAction> blockingActions() {
      List<GameAction> result = new ArrayList<GameAction>();
      for (QueuedAction queuedAction : getBlockingQueuedActions())
        result.add(queuedAction.gameAction());
      return result;
    }
    private List<QueuedAction> getBlockingQueuedActions() {
      List<QueuedAction> result = new ArrayList<QueuedAction>();
      for (QueuedAction action : actions) {
        // Break out when there are non-blocking actions
        if (action.isBlocking() && result.size() > 0)
          break;
        // Add either blocking or non-blocking action
        result.add(action);
        if (action.isBlocking())
          break;
      }
      return result;
    }
    private QueuedAction getFirst() {
      return actions.size() == 0 ? null : actions.get(0);
    }

    /** HashMap cannot guarantee order of insertion, TreeMap is not supported by GWT, a list of
     * encapsulated GameAction seems a good solution */
    private class QueuedAction implements Serializable {
      private GameAction gameAction;
      private boolean blocking;

      public QueuedAction(GameAction gameAction, boolean blocking) {
        this.gameAction = gameAction;
        this.blocking = blocking;
      }
      public GameAction gameAction() {
        return gameAction;
      }
      public boolean isBlocking() {
        return blocking;
      }
    }

    @Override public boolean hasPendingBotActions() {
      return pendingBotActions().size() > 0;
    }
    public List<GameAction> pendingBotActions() {
      // Grab the blocking game actions in the queue
      List<GameAction> blockingActions = blockingActions();
      // Compile a list of actions to be performed by bots
      List<GameAction> botActions = new ArrayList<GameAction>();
      for (GameAction action : blockingActions)
        if (action.player().bot() != null)
          botActions.add(action);
      return botActions;
    }
    @Override public GameAction blockingActions(GamePlayer player) {
      List<GameAction> playerBlockingActions = new ArrayList<GameAction>();
      // Grab the blocking game actions in the queue
      List<GameAction> allBlockingActions = blockingActions();
      // Compile a list of actions to be performed by given player
      for (GameAction action : allBlockingActions)
        if (action.player().equals(player))
          playerBlockingActions.add(action);
      if (playerBlockingActions.size() > 1)
        throw new AssertionError("Expected only to be one action in the list of blocking actions");
      return playerBlockingActions.size() > 0 ? playerBlockingActions.get(0) : null;
    }
    @Override public ActionsQueue forceNext(GameAction gameAction) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HandlerRegistration addActionQueueChangedHandler(
            ActionQueueChangedHandler handler) {
      return eventBus.addHandler(ActionQueueChangedEvent.getType(), handler);
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      eventBus.fireEvent(event);
    }
  }
}
