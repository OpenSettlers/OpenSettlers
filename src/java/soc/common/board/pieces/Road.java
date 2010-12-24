package soc.common.board.pieces;


import soc.common.board.Board;
import soc.common.board.HexSide;
import soc.common.board.resources.*;
import soc.common.game.Player;

public class Road extends PlayerPiece implements ISidePiece
{
    public static Road ROAD = new Road();
    private HexSide sideLocation;
    
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
        if (player.getStock().ofType(Road.ROAD).size() == 0) return false;

        // TODO: port to java
        // if (GetRoadBuildPlaces(game, board).Count == 0) return false;

        return true;
    }

    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }

    /* Returns true when the player has enough resources to pay for the road or he has one or more road tokens
     * @see soc.common.board.pieces.PlayerPiece#canPay(soc.common.game.Player)
     */
    @Override
    public boolean canPay(Player player)
    {
        return super.canPay(player) || player.getRoadBuildingTokens() > 0;
    }
}
