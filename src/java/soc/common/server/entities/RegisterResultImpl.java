package soc.common.server.entities;

import soc.common.server.RegisterResult;

public class RegisterResultImpl implements RegisterResult
{
    private static final long serialVersionUID = -6267699019305622873L;
    private boolean success;
    private User user;
    private String failReason;

    public RegisterResultImpl()
    {
    }

    public RegisterResultImpl(User user)
    {
        super();
        this.user = user;
        success = true;
    }

    public RegisterResultImpl(String failReason)
    {
        super();
        this.failReason = failReason;
        success = false;
    }

    @Override
    public boolean isSuccess()
    {
        return success;
    }

    @Override
    public User getUser()
    {
        return user;
    }

    @Override
    public String getFailReason()
    {
        return failReason;
    }

}
