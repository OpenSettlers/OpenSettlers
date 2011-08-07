package org.soc.common.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.actions.lobby.LobbyAction;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;

public class LobbyLog implements Iterable<LobbyAction>
{
    private List<LobbyAction> lobbyActions = new ArrayList<LobbyAction>();
    private Lobby lobby;
    private EventBus eventBus;

    public LobbyLog(Lobby lobby, EventBus eventBus)
    {
        super();
        this.lobby = lobby;
        this.eventBus = eventBus;
    }

    @Override
    public Iterator<LobbyAction> iterator()
    {
        return lobbyActions.iterator();
    }

    public HandlerRegistration addLobbyActionEventHandler(
            LobbyActionEventHandler handler)
    {
        return eventBus.addHandler(LobbyActionEvent.TYPE, handler);
    }

    public void addAction(LobbyAction action)
    {
        lobbyActions.add(action);
        eventBus.fireEvent(new LobbyActionEvent(action));
    }
}
