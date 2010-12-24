package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.resources.*;
import soc.common.board.routing.IGraphElement;
import soc.common.board.routing.IGraphPoint;
import soc.common.game.IVictoryPointItem;
import soc.common.game.Player;

public class City extends PlayerPiece implements IVictoryPointItem, IPointPiece
{
    public static City CITY = new City();
    private HexPoint pointLocation;
    
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
        if (player.getStock().ofType(City.CITY).size() == 0) 
            return false;
        
        // And we need a town to replace.
        if (player.getBuildPieces().ofType(Town.TOWN).size() == 0)
            return false;
        
        return true;
    }

    /*
     * City is worth 2 victory points
     * @see soc.common.game.IVictoryPointItem#amount()
     */
    @Override
    public int getVictoryPoints()
    {
        return 2;
    }

    public HexPoint getPoint()
    {
        return pointLocation;
    }

    @Override
    public IPointPiece setPoint(HexPoint point)
    {
        pointLocation = point;
        
        return this;
    }

}
