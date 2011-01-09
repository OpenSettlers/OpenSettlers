package soc.common.game.gamePhase;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerList;

public class LobbyGamePhase extends AbstractGamePhase
{
    private List<GamePlayer> playersWhoAcceptedSettings = new ArrayList<GamePlayer>();

    /**
     * @return the playersWhoAcceptedSettings
     */
    public List<GamePlayer> getPlayersWhoAcceptedSettings()
    {
        return playersWhoAcceptedSettings;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new DetermineFirstPlayerGamePhase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.
     * gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(GameAction action, Game game)
    {
        action.perform(game);
    }

    public void resetPlayersWhoAcceptedSettings(
            GamePlayer playerChangingSettings)
    {
        // Create a list of players to remove
        GamePlayerList playersToRemove = new GamePlayerList();
        for (GamePlayer player : playersWhoAcceptedSettings)
        {
            if (player != playerChangingSettings)
            {
                playersToRemove.add(player);
            }
        }

        // Invalidate players
        playersWhoAcceptedSettings.remove(playersToRemove);
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Wait for players to join";
    }

    /*
     * First on turn is simply the first player
     * 
     * @see
     * soc.common.game.gamePhase.AbstractGamePhase#nextTurn(soc.common.game.
     * Game)
     */
    @Override
    public Turn nextTurn(Game game)
    {
        return new TurnImpl().setPlayer(game.getPlayers().get(0));
    }
}
