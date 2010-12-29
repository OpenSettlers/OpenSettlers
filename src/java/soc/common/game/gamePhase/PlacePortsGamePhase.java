package soc.common.game.gamePhase;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.turnActions.seaFarers.PlacePort;
import soc.common.annotations.SeaFarers;
import soc.common.board.ports.Port;
import soc.common.board.territories.Territory;
import soc.common.game.Game;

@SeaFarers
public class PlacePortsGamePhase extends AbstractGamePhase
{
    /*
     * @see
     * soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.
     * gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(AbstractGameAction action, Game game)
    {
        action.perform(game);
    }

    /*
     * Adds a PlacePort action for each port on the map, starting with the game
     * starter (highroller player)
     * 
     * @see soc.common.game.gamePhase.GamePhase#start(soc.common.game.Game)
     */
    @Override
    public void start(Game game)
    {
        int portCount = 0;

        for (Territory t : game.getBoard().getTerritories())
        {
            for (@SuppressWarnings("unused")
            Port port : t.getPorts())
            {
                game.getActionsQueue().enqueue(
                        new PlacePort()
                        /*
                         * Placing ports goes chronologically starting with the
                         * winner. The first player always has the advantage: -
                         * For example with 5 ports and 4 players, first player
                         * may place twice while the rest only once. - First
                         * player may place first, conveniently placing port
                         * alongside - Since port stack is open, first player
                         * placing last port is 100% certain known port
                         */

                        // pass territoryID such that player knows to expect
                        // possible port locations
                                .setTerritoryID(t.getID()).setPlayer(
                                        game.getPlayers().get(
                                                portCount
                                                        % game.getPlayers()
                                                                .size())));

                portCount++;
            }
        }

        game.getActionsQueue().enqueue(new GamePhaseHasEnded().setSender(0));
    }

    /*
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new InitialPlacementGamePhase();
    }
}
