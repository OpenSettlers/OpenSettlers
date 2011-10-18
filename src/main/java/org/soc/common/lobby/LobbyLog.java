package org.soc.common.lobby;

import java.util.*;

import org.soc.common.lobby.ELobbyActionEvent.ELobbyActionHandler;
import org.soc.common.lobby.ELobbyActionEvent.HasELobbyActionHandlers;
import org.soc.common.lobby.actions.*;

import com.google.gwt.event.shared.*;
import com.gwtplatform.dispatch.annotation.*;

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
