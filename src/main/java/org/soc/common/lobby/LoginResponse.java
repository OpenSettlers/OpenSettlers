package org.soc.common.lobby;

import java.io.Serializable;
import java.util.List;

import org.soc.common.game.UserList;
import org.soc.common.game.actions.ValidateResult;
import org.soc.common.server.GameDto;
import org.soc.common.server.entities.User;

public interface LoginResponse extends Serializable {
  public UserList getLoggedInUsers();
  public List<GameDto> getLobbyGames();
  public User getUser();
  public LoginResponse setUser(User user);
  public ValidateResult isAuthenticated();
}
