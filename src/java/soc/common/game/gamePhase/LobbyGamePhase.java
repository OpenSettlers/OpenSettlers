package soc.common.game.gamePhase;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.*;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.PlayerList;
import soc.common.game.PlayerListImpl;

public class LobbyGamePhase extends AbstractGamePhase
{
    public List<Player> playersWhoAcceptedSettings = new ArrayList<Player>();
    
    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new DetermineFirstPlayerGamePhase(); 
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(AbstractGameAction action, Game game)
    {
        action.perform(game);
    }

    public void resetPlayersWhoAcceptedSettings(Player playerChangingSettings)
    {
        // Create a list of players to remove 
        PlayerList playersToRemove = new PlayerListImpl();
        for (Player player : playersWhoAcceptedSettings)
        {
            if (player != playerChangingSettings)
            {
                playersToRemove.add(player);
            }
        }
        
        // Invalidate players
        playersWhoAcceptedSettings.remove(playersToRemove);
    }
}
