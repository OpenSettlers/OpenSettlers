package soc.common.game.phases;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerList;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwtClient.images.Resources;

public class LobbyGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = -2536825366866151136L;
    private List<GamePlayer> playersWhoAcceptedSettings = new ArrayList<GamePlayer>();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, Resources.icons()
                        .lobbyGamePhase32());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

    };

    /** @return the playersWhoAcceptedSettings */
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
            if (player != playerChangingSettings)
                playersToRemove.add(player);

        // Invalidate players
        playersWhoAcceptedSettings.clear();
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
        return new TurnImpl(game.getPlayers().get(0));
    }

    @Override
    public boolean isLobby()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
                    GamePhaseStatusWidgetFactory factory)
    {
        return factory.createLobbyStatusWidget(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
