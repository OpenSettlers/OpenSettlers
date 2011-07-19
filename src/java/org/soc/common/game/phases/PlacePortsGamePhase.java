package org.soc.common.game.phases;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.seaFarers.PlacePort;
import org.soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.ports.Port;
import org.soc.common.board.territories.Territory;
import org.soc.common.game.Game;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;

@SeaFarers
public class PlacePortsGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = -5600692199083296067L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null);

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

    /*
     * @see
     * org.soc.common.game.gamePhase.GamePhase#performAction(org.soc.common.actions.
     * gameAction.GameAction, org.soc.common.game.Game)
     */
    @Override
    public void performAction(GameAction action, Game game)
    {
        action.perform(game);
    }

    /*
     * Adds a PlacePort action for each port on the map, starting with the game
     * starter (highroller player)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#start(org.soc.common.game.Game)
     */
    @Override
    public void start(Game game)
    {
        int portCount = 0;

        // Enqueue PlacePort actions for every port found on the board
        for (Territory t : game.getBoard().getTerritories())
            for (@SuppressWarnings("unused")
            Port port : t.getPorts())
            {
                game.getActionsQueue()
                                .enqueue(new PlacePort()
                                /*
                                 * Placing ports goes chronologically starting with the
                                 * winner. The first player always has the advantage: -
                                 * For example with 5 ports and 4 players, first player
                                 * may place twice while the rest only once. - First
                                 * player may place first, conveniently placing port
                                 * alongside - Since port stack is open, first player
                                 * placing last port is 100% certain known port
                                 */

                                // pass territoryID such that player
                                // knows to expect
                                // possible port locations
                                                .setTerritoryID(t.getID())
                                                .setPlayer(game.getPlayers()
                                                                .get(portCount
                                                                                % game.getPlayers()
                                                                                                .size())),
                                // :P nice work autoformat ;)
                                                true);

                portCount++;
            }

        // Mark end of this phase
        game.getActionsQueue()
                        .enqueue((GameAction) new GamePhaseHasEnded()
                                        .setSender(0),
                                        true);
    }

    /*
     * @see org.soc.common.game.gamePhase.GamePhase#next(org.soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new InitialPlacementGamePhase();
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Place ports";
    }

    @Override
    public boolean isPlacePorts()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
                    GamePhaseStatusWidgetFactory factory)
    {
        return factory.createPlacePortsStatusWidget(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}