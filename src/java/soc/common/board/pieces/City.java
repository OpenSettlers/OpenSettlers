package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.resources.*;
import soc.common.game.Player;

public class City extends PlayerPiece
{
    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();
        
        result.add(new Wheat());
        result.add(new Wheat());
        result.add(new Ore());
        result.add(new Ore());
        result.add(new Ore());
        
        return result;
    }

    @Override
    public String toString()
    {
        return "City";
    }

    /* (non-Javadoc)
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board, soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, Player player)
    {
        // We need a city in stock...
        if (player.getStock().ofType(this).size() == 0) 
            return false;
        
        // And we need a town to replace.
        if (player.getTowns().size() == 0)
            return false;
        
        return true;
    }
}
