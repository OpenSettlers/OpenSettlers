package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.PlacePort;
import soc.common.annotations.SeaFarers;
import soc.common.board.Territory;
import soc.common.board.ports.Port;
import soc.common.game.Game;

@SeaFarers
public class PlacePortsGamePhase extends GamePhase
{
    /* 
     * @see soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(GameAction action, Game game)
    {
        action.perform(game);

        // When queue is empty, it means all ports are set
        if (game.getActionsQueue().size() == 0)
        {
            // Notify we want to start the placement phase
            game.getActionsQueue().enqueue
            (
                new GamePhaseHasEnded()
                    .setSender(0)
             );
        }
        else
        {
            // Move to the next player
            game.setPlayerOnTurn(game.getNextPlayer());
        }
    }

    /*
     * Adds a PlacePort action for each port on the map,
     * starting with the game starter (highroller player)
     * 
     * @see soc.common.game.gamePhase.GamePhase#start(soc.common.game.Game)
     */
    @Override
    public void start(Game game)
    {
        int portCount = 0;

        for (Territory t : game.getBoard().getTerritories())
        {
            for (Port port : t.getPorts())
            {
                game.getActionsQueue().enqueue
                (
                    new PlacePort()
                        /*
                           Placing ports goes chronologically starting with the winner.
                           The first player always has the advantage:
                         - For example with 5 ports and 4 players, first player may place twice
                           while the rest only once.
                         - First player may place first, conveniently placing port alongside
                         - Since port stack is open, first player placing last port is 100% certain
                           known port
                        */
                        
                        // pass territoryID such that player knows to expect possible port locations
                        .setTerritoryID(t.getID())
                        .setPlayer(game.getPlayers().get(portCount % game.getPlayers().size()))
                );

                portCount++;
            }
        }
    }

    /* 
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        // TODO Auto-generated method stub
        return new InitialPlacementGamePhase();
    }
}
