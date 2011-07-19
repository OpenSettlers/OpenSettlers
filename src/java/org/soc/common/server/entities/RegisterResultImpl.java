package org.soc.common.server.entities;

import org.soc.common.actions.Valid;
import org.soc.common.actions.ValidateResult;
import org.soc.common.server.RegisterResult;

public class RegisterResultImpl implements RegisterResult
{
    private static final long serialVersionUID = -6267699019305622873L;
    private User user;
    private ValidateResult registered;

    public RegisterResultImpl()
    {
    }

    public RegisterResultImpl(User user)
    {
        this.user = user;
        this.registered = new Valid();
    }

    public RegisterResultImpl(ValidateResult registered)
    {
        this.registered = registered;
    }

    @Override
    public User getUser()
    {
        return user;
    }

    @Override
    public ValidateResult isRegistered()
    {
        return registered;
    }
}
