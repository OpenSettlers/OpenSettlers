package org.soc.common.lobby;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.lobby.actions.LobbyAction;
import org.soc.common.server.ELobbyActionEvent;
import org.soc.common.server.ELobbyActionEvent.ELobbyActionHandler;
import org.soc.common.server.ELobbyActionEvent.HasELobbyActionHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class LobbyLog implements Iterable<LobbyAction>,
        HasELobbyActionHandlers
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
  @Override public Iterator<LobbyAction> iterator()
  {
    return lobbyActions.iterator();
  }
  public HandlerRegistration addLobbyActionEventHandler(
          ELobbyActionHandler handler)
  {
    return eventBus.addHandler(ELobbyActionEvent.getType(), handler);
  }
  public void addAction(LobbyAction action)
  {
    lobbyActions.add(action);
    eventBus.fireEvent(new ELobbyActionEvent(action));
  }

  @GenEvent public class ELobbyAction {
    @Order(0) LobbyAction lobbyAction;
  }

  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addELobbyActionHandler(ELobbyActionHandler handler) {
    return eventBus.addHandler(ELobbyActionEvent.getType(), handler);
  }
}
