package soc.common.server;

import soc.common.actions.lobby.LobbyAction;

import com.google.gwt.event.shared.GwtEvent;

public class LobbyActionEvent extends GwtEvent<LobbyActionEventHandler>
{
    public static Type<LobbyActionEventHandler> TYPE = new Type<LobbyActionEventHandler>();
    private LobbyAction lobbyAction;

    /**
     * @return the lobbyAction
     */
    public LobbyAction getLobbyAction()
    {
        return lobbyAction;
    }

    public LobbyActionEvent(LobbyAction lobbyAction)
    {
        super();
        this.lobbyAction = lobbyAction;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LobbyActionEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(LobbyActionEventHandler handler)
    {
        handler.onLobbyAction(this);
    }

}
