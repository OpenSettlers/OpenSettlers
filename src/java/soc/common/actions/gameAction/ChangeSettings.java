package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class ChangeSettings extends AbstractGameAction
{
    private static final long serialVersionUID = -3965573991881671035L;

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

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        // Invalidate all other players
        LobbyGamePhase lobbyPhase = (LobbyGamePhase)game.getCurrentPhase();
        lobbyPhase.resetPlayersWhoAcceptedSettings(getPlayer());
        
        super.perform(game);
    }

}
