package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.Board;
import soc.common.board.resources.Clay;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.game.Player;

@SeaFarers
public class Ship extends PlayerPiece
{
    /* (non-Javadoc)
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board, soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, Player player)
    {
        if (player.getStock().ofType(Ship.class).size() == 0) return false;

        // TODO: port to java
        //if (GetShipBuildPlaces(game, board).Count == 0) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see soc.common.board.pieces.PlayerPiece#getCost()
     */
    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();
        
        result.add(new Timber());
        result.add(new Sheep());
        
        return result;
    }
    
    public boolean canMove(Board board, Player player)
    {
        // If there is no ship to move, bugger out
        // TODO: implment Seafarers
        //if (player.getShips().size() == 0)
        //    return false;

        // TODO: port to java
        
        // when can only move a ship once per turn
        //var movedShip = (from MoveShipAction ms in game.GameLog.OfType<MoveShipAction>()
        //                 where ms.TurnID == game.CurrentTurn
        //                 select ms).SingleOrDefault();
        //if (movedShip != null)
        //    return false;

        // We should be able to have a moveable ship, and a location to put the moved ship
        // in order to be able to move te ship
        //if (GetMoveableShips(game, game.Board).Count == 0)
        //    return false;

        return true;    
    }
    
}
