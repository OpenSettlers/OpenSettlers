package org.soc.common.lobby.actions;

import static org.soc.common.game.actions.ValidateResult.Invalid.invalid;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.game.actions.ValidateResult.Valid;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.entities.User;
import org.soc.common.server.lobbyActions.ServerLobbyAction;
import org.soc.common.server.lobbyActions.ServerLobbyActionFactory;

public abstract class AbstractLobbyAction implements LobbyAction {
  protected String message;
  protected transient User user;
  protected int userId;
  protected transient static ValidateResult valid = new Valid();

  @Override public String getMessage() {
    return message;
  }
  @Override public User getUser() {
    return user;
  }
  @Override public LobbyAction setUser(User user) {
    this.user = user;
    if (user != null)
      this.userId = user.id();
    return this;
  }
  @Override public ServerLobbyAction createServerLobbyAction(ServerLobbyActionFactory factory) {
    return factory.createDefaultAction(this);
  }
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result) {
    if (user == null)
      return invalid("User can't be null");
    return valid;
  }
  @Override public int getUserId() {
    return userId;
  }
  @Override public LobbyAction setUserId(int id) {
    this.userId = id;
    return this;
  }
}
