package soc.common.actions.lobby.game;

import soc.common.game.settings.GameSettings;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;

/*
 * Notify all active members of the lobby a game has changed it's settings
 */
public class GameSettingsChanged extends AbstractGameLobbyAction
{
    private static final long serialVersionUID = -283369905155298984L;
    private GameSettings gameSettings;

    /** @return the gameSettings */
    public GameSettings getGameSettings()
    {
        return gameSettings;
    }

    /** @param gameSettings the gameSettings to set */
    public GameSettingsChanged setGameSettings(GameSettings gameSettings)
    {
        this.gameSettings = gameSettings;
        return this;
    }

    /*
     * Searches for game with corresponding gameId and changes it's settings
     * 
     * @see soc.common.actions.lobby.LobbyAction#perform(soc.common.lobby.Lobby)
     */
    @Override
    public void perform(Lobby lobby)
    {
        GameInfo game = lobby.getGames().findById(gameId);
        if (game != null)
            game.setSettings(gameSettings);
    }
}