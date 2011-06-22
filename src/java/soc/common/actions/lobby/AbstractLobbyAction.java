package soc.common.actions.lobby;

import soc.common.actions.Invalid;
import soc.common.actions.Valid;
import soc.common.actions.ValidateResult;
import soc.common.lobby.Lobby;
import soc.common.server.entities.User;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;

public abstract class AbstractLobbyAction implements LobbyAction
{
    private static final long serialVersionUID = -8347682806796145234L;
    protected String message;
    protected transient User user;
    protected int userId;
    protected transient static ValidateResult valid = new Valid();

    @Override
    public String getMessage()
    {
        return message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.LobbyAction#getPlayer()
     */
    @Override
    public User getUser()
    {
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.lobby.LobbyAction#setPlayer(soc.common.server.entities
     * .Player)
     */
    @Override
    public LobbyAction setUser(User user)
    {
        this.user = user;

        if (user != null)
            this.userId = user.getId();

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.lobby.LobbyAction#createServerLobbyAction(soc.common
     * .server.lobbyActions.ServerLobbyActionFactory)
     */
    @Override
    public ServerLobbyAction createServerLobbyAction(
                    ServerLobbyActionFactory factory)
    {
        return factory.createDefaultAction(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.LobbyAction#isValid(soc.common.lobby.Lobby)
     */
    @Override
    public ValidateResult isValid(Lobby lobby, ValidateResult result)
    {
        if (user == null)
            return new Invalid("User can't be null");

        return valid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.LobbyAction#getUserId()
     */
    @Override
    public int getUserId()
    {
        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.LobbyAction#setUserId(int)
     */
    @Override
    public LobbyAction setUserId(int id)
    {
        this.userId = id;
        return this;
    }
}
