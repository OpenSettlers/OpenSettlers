package org.soc.common.lobby.actions;

import static org.soc.common.game.actions.ValidateResult.Invalid.invalid;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.internationalization.I;
import org.soc.common.lobby.Lobby;

/*
 * A player has disconnected and left the server
 */
public class UserLeft extends AbstractLobbyAction
{
  private static final long serialVersionUID = 1885439014105922967L;

  @Override public void perform(Lobby lobby)
  {
    lobby.getUsers().removeUser(user);
  }
  @Override public String getMessage()
  {
    return I.get().lobby().left(user.name());
  }
  /* Invalidates when user is not found in the lobby
   * 
   * @see org.soc.common.actions.lobby.AbstractLobbyAction#isValid(org.soc.common.lobby.Lobby,
   * org.soc.common.actions.ValidateResult) */
  @Override public ValidateResult isValid(Lobby lobby, ValidateResult result)
  {
    ValidateResult newResult = super.isValid(lobby, result);
    if (!newResult.isValid())
      return newResult;
    if (!lobby.getUsers().contains(user))
      return invalid("User not found in the lobby");
    return valid;
  }
}
