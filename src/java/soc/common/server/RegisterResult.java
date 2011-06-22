package soc.common.server;

import java.io.Serializable;

import soc.common.actions.ValidateResult;
import soc.common.server.entities.User;

public interface RegisterResult extends Serializable
{
    public User getUser();

    public ValidateResult isRegistered();
}
