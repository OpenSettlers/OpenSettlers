package soc.common.game.logs;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/*
 * A list of queued actions. This aids the user in what to expect from them, they
 * can actually see a list of things they must do.
 * 
 * The actions blocking gameplay from advancing can be of three types:
 * 1. The first action in the queue where isServer=true. This action
 *    will be executed immediately after the current executing action
 * 2. First encountered action where isBlocking=true
 * 3. All actions of which isBlocking=false
 */
public class ActionsQueueImpl implements ActionsQueue
{
    private static final long serialVersionUID = -8555444373631283907L;
    private List<QueuedAction> actions = new ArrayList<QueuedAction>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public void enqueue(GameAction gameAction, boolean blocking)
    {
        actions.add(new QueuedAction(gameAction, blocking));
        eventBus.fireEvent(new ActionQueueChangedEvent(null, gameAction));
    }

    @Override
    public GameAction peek()
    {
        return getFirst() == null ? null : getFirst().getGameAction();
    }

    @Override
    public GameAction dequeue()
    {
        QueuedAction queuedAction = getFirst();
        actions.remove(queuedAction);
        eventBus.fireEvent(new ActionQueueChangedEvent(queuedAction
                .getGameAction(), null));
        return queuedAction.getGameAction();
    }

    @Override
    public GameAction dequeueExpected(GameAction expectedAction)
    {
        QueuedAction actionToRemove = findExpectedQueuedAction(expectedAction);
        if (actions.remove(actionToRemove))
        {
            eventBus.fireEvent(new ActionQueueChangedEvent(actionToRemove
                    .getGameAction(), null));
            return expectedAction;
        }
        else
        {
            throw new AssertionError("Expected given action to be in queue");
        }
    }

    /*
     * Returns true when given action is expected with relation to the
     * gamestate.
     * 
     * @see
     * soc.common.game.logs.IActionsQueue#isExpected(soc.common.actions.gameAction
     * .GameAction, soc.common.game.Game)
     */
    @Override
    public GameAction findExpected(GameAction prototype)
    {
        QueuedAction expected = findExpectedQueuedAction(prototype);
        return expected == null ? null : expected.getGameAction();
    }

    private QueuedAction findExpectedQueuedAction(GameAction prototype)
    {
        // First action which is a server action is always expected
        if (actions.size() > 0 && getFirst().getGameAction().isServer())
            return getFirst();

        for (QueuedAction blockingAction : getBlockingQueuedActions())
        {
            if (blockingAction.getGameAction().getClass() == prototype
                    .getClass()
                    && blockingAction.getGameAction().getPlayer().equals(
                            prototype.getPlayer()))
                return blockingAction;
        }

        return null;
    }

    @Override
    public int size()
    {
        return actions.size();
    }

    @Override
    public void enqueuePriority(GameAction gameAction, boolean blocking)
    {
        actions.add(0, new QueuedAction(gameAction, blocking));
        eventBus.fireEvent(new ActionQueueChangedEvent(null, gameAction));
    }

    @Override
    public boolean isWaitingForActions()
    {
        return getFirst() == null ? false : getFirst().isBlocking();
    }

    /*
     * Returns the first encountered blocking action or all the encountered
     * non-blocking actions
     * 
     * @see soc.common.game.logs.ActionsQueue#getBlockingActions()
     */
    public List<GameAction> getBlockingActions()
    {
        List<GameAction> result = new ArrayList<GameAction>();

        for (QueuedAction queuedAction : getBlockingQueuedActions())
            result.add(queuedAction.getGameAction());

        return result;
    }

    private List<QueuedAction> getBlockingQueuedActions()
    {
        List<QueuedAction> result = new ArrayList<QueuedAction>();

        for (QueuedAction action : actions)
        {
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

    @Override
    public HandlerRegistration addQueueChangedEventHandler(
            ActionQueueChangedEventHandler handler)
    {
        return eventBus.addHandler(ActionQueueChangedEvent.TYPE, handler);
    }

    private QueuedAction getFirst()
    {
        return actions.size() == 0 ? null : actions.get(0);
    }

    /*
     * HashMap cannot guarantee order of insertion, TreeMap is not supported by
     * GWT, a list of encapsulated GameAction seems a good solution
     */
    private class QueuedAction
    {
        private GameAction gameAction;
        private boolean blocking;

        public QueuedAction(GameAction gameAction, boolean blocking)
        {
            super();
            this.gameAction = gameAction;
            this.blocking = blocking;
        }

        public GameAction getGameAction()
        {
            return gameAction;
        }

        public boolean isBlocking()
        {
            return blocking;
        }
    }

}
