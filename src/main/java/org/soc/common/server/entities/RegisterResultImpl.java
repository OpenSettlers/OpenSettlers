package org.soc.common.server.entities;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.game.actions.ValidateResult.Valid;
import org.soc.common.server.RegisterResult;

public class RegisterResultImpl implements RegisterResult {
  private User user;
  private ValidateResult registered;

  public RegisterResultImpl() {}
  public RegisterResultImpl(User user)
  {
    this.user = user;
    this.registered = new Valid();
  }
  public RegisterResultImpl(ValidateResult registered) {
    this.registered = registered;
  }
  @Override public User getUser() {
    return user;
  }
  @Override public ValidateResult isRegistered() {
    return registered;
  }
}
