package soc.common.actions.lobby;

import soc.common.actions.ValidateResult;
import soc.common.actions.Invalid;
import soc.common.internationalization.I18n;
import soc.common.lobby.Lobby;

/*
 * A player has disconnected and left the server
 */
public class UserLeft extends AbstractLobbyAction
{
    private static final long serialVersionUID = 1885439014105922967L;

    @Override
    public void perform(Lobby lobby)
    {
        lobby.getUsers().removeUser(user);
    }

    @Override
    public String getMessage()
    {
        return I18n.get().lobby().left(user.getName());
    }

    /*
     * Invalidates when user is not found in the lobby
     * 
     * @see soc.common.actions.lobby.AbstractLobbyAction#isValid(soc.common.lobby.Lobby,
     * soc.common.actions.ValidateResult)
     */
    @Override
    public ValidateResult isValid(Lobby lobby, ValidateResult result)
    {
        ValidateResult newResult = super.isValid(lobby, result);

        if (!newResult.isValid())
            return newResult;

        if (!lobby.getUsers().contains(user))
            return new Invalid("User not found in the lobby");

        return validated;
    }

}
