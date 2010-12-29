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
 */
public class ActionsQueue implements IActionsQueue
{
    private List<QueuedAction> actions = new ArrayList<QueuedAction>();
    private SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public void enqueue(GameAction inGameAction)
    {
        QueuedAction queuedAction = new QueuedAction(inGameAction, false, false);
        enqueue(queuedAction);
    }

    @Override
    public void enqueue(QueuedAction queuedAction)
    {
        actions.add(queuedAction);
        eventBus.fireEvent(new ActionQueueChangedEvent(queuedAction, null));
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
        return queuedAction;
    }

    /*
     * Returns true when given action is expected with relation to the gamestate
     * 
     * @see
     * soc.common.game.logs.IActionsQueue#isExpected(soc.common.actions.gameAction
     * .GameAction, soc.common.game.Game)
     */
    @Override
    public boolean isExpected(GameAction action, Game game)
    {
        // TODO: implement

        return false;
    }

    @Override
    public int size()
    {
        return actions.size();
    }

    @Override
    public GameAction peekAction()
    {
        return actions.get(0).getAction();
    }

    @Override
    public void enqueuePriority(QueuedAction queuedAction)
    {
        actions.add(0, queuedAction);
    }

    @Override
    public boolean isWaitingForActions()
    {
        return actions.get(0).isBlocking();
    }

    public List<GameAction> getBlockingActions()
    {
        List<GameAction> result = new ArrayList<GameAction>();

        for (int i = 0; i < actions.size(); i++)
        {
            if (actions.get(i).isBlocking())
            {
                result.add(actions.get(i).getAction());
            }
            else
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
}
