package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.resources.*;
import soc.common.game.IVictoryPointItem;
import soc.common.game.Player;

public class Town extends PlayerPiece implements IVictoryPointItem, IPointPiece
{
    public static Town TOWN  = new Town();
    private HexPoint pointLocation;
    
    /**
     * @return the pointLocation
     */
    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    /**
     * @param pointLocation the pointLocation to set
     */
    @Override
    public IPointPiece setPoint(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;
    
        return this;
    }

    @Override
    public String toString()
    {
        return "Town";
    }

    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();
        
        result.add(new Timber());
        result.add(new Wheat());
        result.add(new Clay());
        result.add(new Sheep());
        
        return result;
    }

    /* (non-Javadoc)
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board, soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, Player player)
    {
        // We need a town in stock...
        if (player.getStock().ofType(Town.class).size() == 0) 
            return false;
        
        // And we need a place to put it onto
        
        // TODO: port to java
        //if (GetTownBuildPlaces(game, board).Count == 0) return false;
        
        return true;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }
}
