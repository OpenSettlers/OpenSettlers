package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

public class AcceptSettings extends AbstractGameAction
{
    private static final long serialVersionUID = 8181882830703417988L;

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return (gamePhase instanceof LobbyGamePhase);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        // Add the player to the list of players who accepted the settings
        LobbyGamePhase lobbyPhase = (LobbyGamePhase) game.getCurrentPhase();
        if (!lobbyPhase.playersWhoAcceptedSettings.contains(getPlayer()))
        {
            lobbyPhase.playersWhoAcceptedSettings.add(getPlayer());
        }

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().acceptSettingsToDo(player.getName());
    }
}
