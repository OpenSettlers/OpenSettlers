package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexSide;
import soc.common.board.resources.Clay;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Timber;
import soc.common.game.GamePlayer;

public class Road extends AbstractPlayerPiece implements SidePiece
{
    private static final long serialVersionUID = -6137419255953696891L;
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

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board,
     * soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, GamePlayer player)
    {
        if (player.getStock().ofType(Road.ROAD).size() == 0)
            return false;

        // TODO: port to java
        // if (GetRoadBuildPlaces(game, board).Count == 0) return false;

        return true;
    }

    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }

    /*
     * Returns true when the player has enough resources to pay for the road or
     * he has one or more road tokens
     * 
     * @see soc.common.board.pieces.PlayerPiece#canPay(soc.common.game.Player)
     */
    @Override
    public boolean canPay(GamePlayer player)
    {
        return super.canPay(player) || player.getRoadBuildingTokens() > 0;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }
}
