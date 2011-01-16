package soc.common.game.logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    private Map<GameAction, Boolean> actions = new HashMap<GameAction, Boolean>();
    private SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public void enqueue(GameAction gameAction, boolean blocking)
    {
        actions.put(gameAction, blocking);
        eventBus.fireEvent(new ActionQueueChangedEvent(null, gameAction));
    }

    @Override
    public GameAction peek()
    {
        return getFirst();
    }

    @Override
    public GameAction dequeue()
    {
        GameAction queuedAction = getFirst();
        actions.remove(queuedAction);
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
    public GameAction findExpected(GameAction actionType, Game game)
    {
        // First action which is a server action is always expected
        if (actions.size() > 0 && getFirst().isServer())
            return getFirst();

        for (GameAction blockingAction : getBlockingActions())
        {
            if (blockingAction.getClass() == actionType.getClass()
                    && blockingAction.getPlayer()
                            .equals(actionType.getPlayer()))
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
        actions.put(gameAction, blocking);
        eventBus.fireEvent(new ActionQueueChangedEvent(null, gameAction));
    }

    @Override
    public boolean isWaitingForActions()
    {
        return actions.get(getFirst());
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

        for (Entry<GameAction, Boolean> entry : actions.entrySet())
        {
            // Break out when there are non-blocking actions
            if (entry.getValue() && result.size() > 0)
                break;

            // Add either blocking or non-blocking action
            result.add(entry.getKey());
            if (entry.getValue())
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

    private GameAction findByAction(GameAction action)
    {
        for (GameAction queuedAction : actions.keySet())
        {
            if (queuedAction.equals(action))
            {
                return queuedAction;
            }
        }

        return null;
    }

    private GameAction getFirst()
    {
        return actions.size() == 0 ? null : actions.keySet().iterator().next();
    }
}
