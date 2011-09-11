package org.soc.gwt.client.lobby;

import org.soc.common.lobby.LoginResponse;

import com.google.gwt.event.shared.GwtEvent;

public class LoggedInEvent extends GwtEvent<LoggedInEventHandler>
{
    public static Type<LoggedInEventHandler> TYPE = new Type<LoggedInEventHandler>();
    private LoginResponse loginResponse;

    public LoggedInEvent(LoginResponse loginResponse)
    {
        super();
        this.loginResponse = loginResponse;
    }

    /**
     * @return the loginResponse
     */
    public LoginResponse getLoginResponse()
    {
        return loginResponse;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LoggedInEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(LoggedInEventHandler handler)
    {
        handler.onLoggedIn(this);
    }

}
