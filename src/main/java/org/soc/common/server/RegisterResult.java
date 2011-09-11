package org.soc.common.server;

import java.io.Serializable;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.server.entities.User;


public interface RegisterResult extends Serializable
{
    public User getUser();

    public ValidateResult isRegistered();
}
