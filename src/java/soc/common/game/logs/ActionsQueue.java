package soc.common.game.logs;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

/*
 * A list of queued actions. This aids the user in what to expect from them, they
 * can actually see a list of things they must do.
 */
public class ActionsQueue implements IActionsQueue
{
    private List<QueuedAction> actions = new ArrayList<QueuedAction>();
    private SimpleEventBus eventBus; 
    
    private void safelyFireEvent(ActionQueueChangedEvent event)
    {
        if (eventBus !=null)
        {
            eventBus.fireEvent(event);
        }
    }
    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }
        
        return eventBus;
    }
    
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
        safelyFireEvent(new ActionQueueChangedEvent(queuedAction, null));
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
     * @see soc.common.game.logs.IActionsQueue#isExpected(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public boolean isExpected(GameAction action, Game game)
    {
        int i=0;
        //TODO: implement
        
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
}
