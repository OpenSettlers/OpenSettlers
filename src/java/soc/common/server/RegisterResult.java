package soc.common.server;

import java.io.Serializable;

import soc.common.server.entities.User;

public interface RegisterResult extends Serializable
{
    public boolean isSuccess();
    public User getUser();
    public String getFailReason();
}
