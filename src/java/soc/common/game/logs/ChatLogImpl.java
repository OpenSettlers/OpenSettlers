package soc.common.game.logs;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.meta.GameChat;

import com.google.gwt.event.shared.SimpleEventBus;

public class ChatLogImpl implements ChatLog
{
    private static final long serialVersionUID = 6288111035333874654L;
    private List<GameChat> chats = new ArrayList<GameChat>();
    private transient SimpleEventBus eventBus;

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
    public ChatLog copy()
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
