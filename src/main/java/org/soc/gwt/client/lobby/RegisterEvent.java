package org.soc.gwt.client.lobby;

import com.google.gwt.event.shared.GwtEvent;

public class RegisterEvent extends GwtEvent<RegisterEventHandler>
{
    public static Type<RegisterEventHandler> TYPE = new Type<RegisterEventHandler>();
    private String nickname;
    private String password;

    public RegisterEvent(String nickname, String password)
    {
        super();
        this.nickname = nickname;
        this.password = password;
    }

    /**
     * @return the nickname
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    @Override
    protected void dispatch(RegisterEventHandler handler)
    {
        handler.onRegister(this);
    }

    @Override
    public Type<RegisterEventHandler> getAssociatedType()
    {
        return TYPE;
    }
}