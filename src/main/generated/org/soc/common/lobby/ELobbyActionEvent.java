package org.soc.common.lobby;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ELobbyActionEvent extends GwtEvent<ELobbyActionEvent.ELobbyActionHandler> { 

  public interface HasELobbyActionHandlers extends HasHandlers {
    HandlerRegistration addELobbyActionHandler(ELobbyActionHandler handler);
  }

  public interface ELobbyActionHandler extends EventHandler {
    public void onELobbyAction(ELobbyActionEvent event);
  }

  private static final Type<ELobbyActionHandler> TYPE = new Type<ELobbyActionHandler>();

  public static void fire(HasHandlers source, org.soc.common.lobby.actions.LobbyAction lobbyAction) {
    source.fireEvent(new ELobbyActionEvent(lobbyAction));
  }

  public static Type<ELobbyActionHandler> getType() {
    return TYPE;
  }

  org.soc.common.lobby.actions.LobbyAction lobbyAction;

  public ELobbyActionEvent(org.soc.common.lobby.actions.LobbyAction lobbyAction) {
    this.lobbyAction = lobbyAction;
  }

  protected ELobbyActionEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ELobbyActionHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.lobby.actions.LobbyAction getLobbyAction() {
    return lobbyAction;
  }

  @Override
  protected void dispatch(ELobbyActionHandler handler) {
    handler.onELobbyAction(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ELobbyActionEvent other = (ELobbyActionEvent) obj;
    if (lobbyAction == null) {
      if (other.lobbyAction != null)
        return false;
    } else if (!lobbyAction.equals(other.lobbyAction))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (lobbyAction == null ? 1 : lobbyAction.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ELobbyActionEvent["
                 + lobbyAction
    + "]";
  }
}
