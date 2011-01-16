package soc.common.game.logs;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

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
    private List<QueuedAction> actions = new ArrayList<QueuedAction>();
    private SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public void enqueue(GameAction inGameAction)
    {
        QueuedAction queuedAction = new QueuedAction(inGameAction, true, false);
        enqueue(queuedAction);
    }

    @Override
    public void enqueue(QueuedAction queuedAction)
    {
        actions.add(queuedAction);
        eventBus.fireEvent(new ActionQueueChangedEvent(null, queuedAction));
    }

    @Override
    public QueuedAction peek()
    {
        return actions.get(0);
    }

    @Override
    public QueuedAction dequeue()
    {
        QueuedAction queuedAction = actions.get(0);
        actions.remove(0);
        eventBus.fireEvent(new ActionQueueChangedEvent(queuedAction, null));
        return queuedAction;
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
    public QueuedAction findExpected(GameAction action, Game game)
    {
        // First action which is a server action is always expected
        if (actions.size() > 0 && actions.get(0).getAction().isServer())
        {
            return actions.get(0);
        }

        for (QueuedAction blockingAction : getBlockingActions())
        {
            if (blockingAction.getAction().getClass() == action.getClass()
                    && blockingAction.getAction().getPlayer().equals(
                            action.getPlayer()))
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
    public GameAction peekAction()
    {
        return actions.size() == 0 ? null : actions.get(0).getAction();
    }

    @Override
    public void enqueuePriority(QueuedAction queuedAction)
    {
        actions.add(0, queuedAction);
        eventBus.fireEvent(new ActionQueueChangedEvent(null, queuedAction));
    }

    @Override
    public boolean isWaitingForActions()
    {
        return actions.get(0).isBlocking();
    }

    /*
     * Returns the first encountered blocking action or all the encountered
     * non-blocking actions
     * 
     * @see soc.common.game.logs.ActionsQueue#getBlockingActions()
     */
    public List<QueuedAction> getBlockingActions()
    {
        List<QueuedAction> result = new ArrayList<QueuedAction>();

        for (int i = 0; i < actions.size(); i++)
        {
            QueuedAction action = actions.get(i);

            // Break out when there are non-blocking actions
            if (action.isBlocking() && result.size() > 0)
            {
                break;
            }

            // Add either blocking or non-blocking action
            result.add(action);
            if (action.isBlocking())
            {
                break;
            }
        }

        return result;
    }

    @Override
    public HandlerRegistration addQueueChangedEventHandler(
            ActionQueueChangedEventHandler handler)
    {
        return eventBus.addHandler(ActionQueueChangedEvent.TYPE, handler);
    }

    @Override
    public QueuedAction dequeue(QueuedAction queuedAction)
    {
        if (queuedAction != null)
        {
            actions.remove(queuedAction);
        }
        else
        {
            throw new RuntimeException("Expected dequeued action is contained");
        }

        eventBus.fireEvent(new ActionQueueChangedEvent(queuedAction, null));

        return queuedAction;
    }

    private QueuedAction findByAction(GameAction action)
    {
        for (QueuedAction queuedAction : actions)
        {
            if (queuedAction.getAction().equals(action))
            {
                return queuedAction;
            }
        }

        return null;
    }

    @Override
    public void enqueuePriority(GameAction action)
    {
        QueuedAction queuedAction = new QueuedAction(action);
        enqueuePriority(queuedAction);
    }

}
