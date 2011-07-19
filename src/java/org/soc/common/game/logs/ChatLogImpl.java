package org.soc.common.game.logs;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.actions.gameAction.meta.GameChat;


import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class ChatLogImpl implements ChatLog
{
    private static final long serialVersionUID = 6288111035333874654L;
    private List<GameChat> chats = new ArrayList<GameChat>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public ChatLog copy()
    {
        // ChatLogImpl result = new ChatLogImpl();
        // result.say(new )
        throw new RuntimeException("ChatLog.copy not yet implemented");
    }
    @Override
    public void say(GameChat gameChat)
    {
        chats.add(gameChat);
        eventBus.fireEvent(new SaidEvent(gameChat));
    }

    @Override
    public HandlerRegistration addSaidEventHandler(SaidEventHandler handler)
    {
        return eventBus.addHandler(SaidEvent.TYPE, handler);
    }

}
