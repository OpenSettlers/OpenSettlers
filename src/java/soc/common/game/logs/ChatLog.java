package soc.common.game.logs;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.actions.gameAction.GameChat;

public class ChatLog implements IChatLog
{
    private List<GameChat> chats = new ArrayList<GameChat>();
    private SimpleEventBus eventBus;
    
    private void safelyFireEvent(SaidEvent event)
    {
        if (eventBus != null)
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
    public IChatLog copy()
    {
        throw new RuntimeException("ChatLog.copy not yet implemented");
    }

    @Override
    public void say(GameChat gameChat)
    {
        chats.add(gameChat);
        safelyFireEvent(new SaidEvent(gameChat));
    }
    
    @Override
    public void addSaidEventHandler(SaidEventHandler handler)
    {
        getEventBus().addHandler(SaidEvent.TYPE, handler);
    }

}
