package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.resources.*;
import soc.common.game.Player;

public class Road extends PlayerPiece
{
    public static Road ROAD = new Road();
    @Override
    public String toString()
    {
        return "Road";
    }

    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();
        
        result.add(new Timber());
        result.add(new Clay());
        
        return result;
    }

    /* (non-Javadoc)
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board, soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, Player player)
    {
        if (player.getStock().ofType(Road.class).size() == 0) return false;

        // TODO: port to java
        // if (GetRoadBuildPlaces(game, board).Count == 0) return false;

        return true;
    }
}
